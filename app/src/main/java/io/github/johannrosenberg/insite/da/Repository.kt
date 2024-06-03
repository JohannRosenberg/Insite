package io.github.johannrosenberg.insite.da

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.da.web.PostsPath
import io.github.johannrosenberg.insite.da.web.RetrofitClient
import io.github.johannrosenberg.insite.da.web.WebAPI
import io.github.johannrosenberg.insite.models.AppData
import io.github.johannrosenberg.insite.models.Category
import io.github.johannrosenberg.insite.models.PostDetails
import io.github.johannrosenberg.insite.models.QuizPostings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * The repository used to store and access application data.
 */
class Repository {
    companion object {
        var appData = AppData()

        private var postings = QuizPostings()
        var quizPostings = mutableStateOf(postings)
        val categories = mutableMapOf<String, String>()
        val selectedNavMenuId = mutableStateOf(appData.selectedNavMenuId)
        val selectedCategoryId = mutableStateOf("")
        val showSplashScreen = mutableStateOf(true)

        private const val KEY_APP_DATA = "appData"
        private var webApi: WebAPI = RetrofitClient.createRetrofitClient()

        fun saveSelectedNavMenuItemId(id: String) {
            appData.selectedNavMenuId = id
            appData.selectedCategoryId = id
            saveAppData()
            selectedNavMenuId.value = id
            selectedCategoryId.value = id
        }

        fun loadAppData(onLoaded: () -> Unit) {
            CoroutineScope(Dispatchers.IO).launch {
                postings = webApi.getQuizPostings()

                // Store the categories in a separate map. This allows for
                // a fast lookup instead of having to search through nested
                // lists to find a category name given its id.

                postings.categories.forEach { category ->
                    categories[category.id] = category.name
                    category.categories?.forEach { subCategory ->
                        categories[subCategory.id] = category.name + " > " + subCategory.name
                    }
                }

                quizPostings.value = postings

                val sharedPrefs = App.context.currentActivity?.getPreferences(Context.MODE_PRIVATE)
                var appDataLoaded = false

                if (sharedPrefs != null) {
                    val data = sharedPrefs.getString(KEY_APP_DATA, null)

                    if (data != null) {
                        appData = Json.decodeFromString<AppData>(data)
                        selectedNavMenuId.value = appData.selectedNavMenuId
                        selectedCategoryId.value = appData.selectedCategoryId
                        appDataLoaded = true
                    }
                }

                if (!appDataLoaded) {
                    appData = AppData()
                }

                delay(3000)
                showSplashScreen.value = false
                onLoaded()
            }
        }

        fun saveAppData() {
            CoroutineScope(Dispatchers.IO).launch {
                val sharedPref =
                    App.context.currentActivity?.getPreferences(Context.MODE_PRIVATE) as SharedPreferences
                val json = Json.encodeToString(appData)

                with(sharedPref.edit()) {
                    putString(KEY_APP_DATA, json)
                    apply()
                }
            }
        }

        suspend fun getPostDetails(postId: String): PostDetails {
            try {
                val postDetails = webApi.getPostDetails(PostsPath + postId + ".json")
                return postDetails
            } catch(ex: Exception) {
                return PostDetails()
            }
        }

        fun saveSelectedCategoryId(categoryId: String) {
            var categoryIdToSave = categoryId

            if (categoryId.isEmpty()) {
                val categories = selectedCategoryId.value.split("|")
                categoryIdToSave = categories[0]
            }

            appData.selectedCategoryId = categoryIdToSave
            saveAppData()
            selectedCategoryId.value = categoryIdToSave
        }

        fun isASubCategoryOrHasSubCategories(categoryId: String): Boolean {
            if (categoryId.contains("|")) {
                return true
            }

            return postings.categories.firstOrNull { it.id == categoryId }?.categories != null
        }

        fun getCategoryNameById(id: String): String {
            return categories[id] ?: ""
        }

        fun postBelongsToCategory(postCategoryId: String, categoryId: String): Boolean {
            if (categoryId.contains("|")) {
                return (postCategoryId == categoryId)
            }

            // Check if the post is a sub-category.
            val postCategories = postCategoryId.split("|")
            return postCategories[0] == categoryId

        }

        fun getSubCategories(categoryId: String): List<Category>? {
            var catId = categoryId

            if (categoryId.contains("|")) {
                val categories = categoryId.split("|")
                catId = categories[0]
            }
            return postings.categories.find { it.id == catId }?.categories
        }

        fun getRootCategoryId(categoryId: String): String {
            val categories = categoryId.split("|")
            return categories[0]
        }
    }
}