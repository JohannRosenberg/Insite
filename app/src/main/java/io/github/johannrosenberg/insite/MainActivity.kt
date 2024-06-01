package io.github.johannrosenberg.insite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.johannrosenberg.insite.da.Repository
import io.github.johannrosenberg.insite.ui.screens.ComposableResourceIDs
import io.github.johannrosenberg.insite.ui.screens.main.MainHandler
import io.github.johannrosenberg.insite.ui.theme.InsiteTheme
import io.github.johannrosenberg.insite.ui.theme.MaterialColors
import io.github.johannrosenberg.jetmagic.composables.Image
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

        //enableEdgeToEdge()
        setContent {
            InsiteTheme {
                if (Repository.showSplashScreen.value) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = R.drawable.splash_screen_2560x1440),
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 190.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(text = "Insite", color = MaterialColors.gray300, fontSize = 40.sp, modifier = Modifier.padding(bottom = 10.dp))
                        Text(text = "Challenges for Critical Thinkers", color = MaterialColors.gray300, fontSize = 16.sp)
                    }
                } else {
                    MainHandler()
                }
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
