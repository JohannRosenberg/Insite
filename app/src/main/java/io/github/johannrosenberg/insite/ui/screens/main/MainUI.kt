package io.github.johannrosenberg.insite.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.ui.components.CustomAlertDialogHandler
import io.github.johannrosenberg.insite.ui.components.CustomSnackbarHandler
import io.github.johannrosenberg.insite.ui.components.ModalEditTextHandler
import io.github.johannrosenberg.insite.ui.components.ProgressScreenHandler
import io.github.johannrosenberg.insite.ui.nav.NavDrawerHandler
import io.github.johannrosenberg.jetmagic.composables.ScreenFactoryHandler
import io.github.johannrosenberg.jetmagic.navigation.navman

@Composable
fun MainHandler(modifier: Modifier = Modifier) {
    App.mainViewModel = viewModel()
    val drawerState = App.mainViewModel.drawerState

    //var drawerGesturesEnabled by remember { mutableStateOf(true) }

    navman.observeScreenChange {
        App.mainViewModel.drawerGesturesEnabled.value = (navman.totalScreensDisplayed == 1)
    }

    Main(drawerState, drawerGesturesEnabled = App.mainViewModel.drawerGesturesEnabled.value, modifier = modifier)
}

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(drawerState: DrawerState, drawerGesturesEnabled: Boolean, modifier: Modifier = Modifier) {
    Box {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(drawerContainerColor = Color.Transparent) {
                    NavDrawerHandler(drawerState = drawerState)
                }
            },
            gesturesEnabled = drawerGesturesEnabled,
            content = {
                Scaffold {
                    ScreenFactoryHandler()
                }
            }
        )

        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ProgressScreenHandler()
        }

        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            CustomSnackbarHandler(modifier = modifier)
        }

        CustomAlertDialogHandler()
        ModalEditTextHandler()
    }
}

