package io.github.johannrosenberg.insite.da

import android.content.Context
import android.content.SharedPreferences
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.models.AppData
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
        lateinit var appData: AppData

        private const val KEY_APP_DATA = "appData"

        fun loadAppData(onLoaded: () -> Unit) {
            CoroutineScope(Dispatchers.IO).launch {
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