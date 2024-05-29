package io.github.johannrosenberg.insite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.johannrosenberg.insite.da.Repository
import io.github.johannrosenberg.insite.ui.screens.ComposableResourceIDs
import io.github.johannrosenberg.insite.ui.screens.main.MainHandler
import io.github.johannrosenberg.insite.ui.theme.InsiteTheme
import io.github.johannrosenberg.jetmagic.composables.crm
import io.github.johannrosenberg.jetmagic.navigation.navman
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navman.activity = this

        Repository.loadAppData {
            if (navman.totalScreensDisplayed == 0) {
                CoroutineScope(Dispatchers.Main).launch {
                    navman.goto(composableResId = ComposableResourceIDs.HOME_SCREEN)
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            InsiteTheme {
                MainHandler()
            }
        }
    }

    override fun onBackPressed() {
        if (!navman.goBack())
            super.onBackPressed()
    }

    override fun onDestroy() {
        crm.onConfigurationChanged()
        super.onDestroy()
    }
}
