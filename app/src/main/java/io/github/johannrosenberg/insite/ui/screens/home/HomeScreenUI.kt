package io.github.johannrosenberg.insite.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_BOTTOM
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_END
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_TOP
import io.github.johannrosenberg.insite.ui.screens.main.MainViewModel
import io.github.johannrosenberg.jetmagic.models.ComposableInstance
import io.github.johannrosenberg.jetmagic.models.LocalComposableInstance
import kotlinx.coroutines.launch

@Composable
fun HomeHandler(composableInstance: ComposableInstance) {

    val parentComposableInstance = LocalComposableInstance.current

    CompositionLocalProvider(LocalComposableInstance provides composableInstance) {

        val coroutineScope = rememberCoroutineScope()

        val vmMain: MainViewModel = viewModel()
        val vm = composableInstance.viewmodel as HomeViewModel
        vm.imageManager.onComposableInstanceTerminated(composableInstance = composableInstance)
        composableInstance.onUpdate?.observeAsState()?.value

        //val motionConfig = Repository.getCurrentMotionConfig()

        HomeScreen(
            onToolbarMenuClick = {
                coroutineScope.launch {
                    vmMain.drawerState.open()
                }
            },
)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onToolbarMenuClick: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                modifier = Modifier
                    .height(ScreenGlobals.DEFAULT_APPBAR_HEIGHT)
                    .padding(
                        top = APPBAR_PADDING_TOP,
                        end = APPBAR_PADDING_END,
                        bottom = APPBAR_PADDING_BOTTOM
                    ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //Text(text = motionConfig.name, fontSize = APPBAR_FONT_SIZE)
                    }
                },
                navigationIcon = {
                    Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onToolbarMenuClick) {
                            Icon(
                                tint = MaterialTheme.colorScheme.primary,
                                imageVector = Icons.Filled.Menu,
                                contentDescription = ""
                            )
                        }
                    }
                }
            )
        }
    }
}


