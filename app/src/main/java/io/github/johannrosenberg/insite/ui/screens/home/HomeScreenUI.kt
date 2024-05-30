package io.github.johannrosenberg.insite.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R
import io.github.johannrosenberg.insite.da.Repository
import io.github.johannrosenberg.insite.models.Post
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_FONT_SIZE
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_ICON_SIZE
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

        val quizPostings = Repository.quizPostings.value

        HomeScreen(
            posts = quizPostings.posts,
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
    posts: List<Post>,
    onToolbarMenuClick: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                modifier = Modifier
                    .height(ScreenGlobals.DEFAULT_APPBAR_HEIGHT)

                /*                    .padding(
                                        top = APPBAR_PADDING_TOP,
                                        end = APPBAR_PADDING_END,
                                        bottom = APPBAR_PADDING_BOTTOM
                                    )*/,
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = App.context.getString(R.string.all_categoies),
                            fontSize = APPBAR_FONT_SIZE
                        )
                    }
                },
                navigationIcon = {
                    Row(
                        Modifier.height(ScreenGlobals.DEFAULT_APPBAR_HEIGHT),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onToolbarMenuClick) {
                            Icon(
                                modifier = Modifier.size(APPBAR_ICON_SIZE),
                                tint = MaterialTheme.colorScheme.primary,
                                imageVector = ImageVector.vectorResource(R.drawable.menu),
                                contentDescription = ""
                            )
                        }
                    }
                }
            )

            LazyColumn {
                items(posts) { post ->
                    Column {
                        Row {
                            Text(text = post.title)
                        }
                        Row {
                            Text(text = post.date)
                            Text(text = post.category)
                            Text(text = post.author)
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}


