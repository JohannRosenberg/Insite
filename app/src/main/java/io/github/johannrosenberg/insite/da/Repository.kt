package io.github.johannrosenberg.insite.da

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import earth.topdog.android.da.web.RetrofitClient
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.da.web.WebAPI
import io.github.johannrosenberg.insite.models.AppData
import io.github.johannrosenberg.insite.models.QuizPostings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

        private const val KEY_APP_DATA = "appData"
        private var webApi: WebAPI = RetrofitClient.createRetrofitClient()

        fun saveSelectedNavMenuItemId(id: String) {
            appData.selectedNavMenuId = id
            saveAppData()
            selectedNavMenuId.value = id
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
                        categories[subCategory.id] = subCategory.name
                    }
                }

                quizPostings.value = postings

                val sharedPrefs = App.context.currentActivity?.getPreferences(Context.MODE_PRIVATE)
                var appDataLoaded = false

                if (sharedPrefs != null) {
                    val data = sharedPrefs.getString(KEY_APP_DATA, null)

                    if (data != null) {
                        appData = Json.decodeFromString<AppData>(data)
                        appDataLoaded = true
                    }
                }

                if (!appDataLoaded) {
                    appData = AppData()
                }

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

        fun getCategoryNameById(id: String): String {
            return categories[id] ?: ""
        }

        fun postBelongsToCategory(postCategoryId: String, categoryId: String): Boolean {
            val postCategories = postCategoryId.split("|")
            return postCategories[0] == categoryId

        }
    }
}