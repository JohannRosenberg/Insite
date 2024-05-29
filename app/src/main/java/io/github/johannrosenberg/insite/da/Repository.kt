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

        var quizPostings = mutableStateOf(QuizPostings())

        private const val KEY_APP_DATA = "appData"
        private var webApi: WebAPI = RetrofitClient.createRetrofitClient()

        fun loadAppData(onLoaded: () -> Unit) {
            CoroutineScope(Dispatchers.IO).launch {
                quizPostings.value = webApi.getQuizPostings()

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
    }
}