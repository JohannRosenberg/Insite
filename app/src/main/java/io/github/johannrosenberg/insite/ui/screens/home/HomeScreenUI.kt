package io.github.johannrosenberg.insite.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R
import io.github.johannrosenberg.insite.da.Repository
import io.github.johannrosenberg.insite.models.QuizPostings
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_FONT_SIZE
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_ICON_SIZE
import io.github.johannrosenberg.insite.ui.screens.main.MainViewModel
import io.github.johannrosenberg.insite.ui.theme.MaterialColors
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


        HomeScreen(
            quizPostings = Repository.quizPostings.value,
            onToolbarMenuClick = {
                coroutineScope.launch {
                    vmMain.drawerState.open()
                }
            },
            onPostClick = { id ->

            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    quizPostings: QuizPostings,
    onToolbarMenuClick: () -> Unit,
    onPostClick: (postId: String) -> Unit
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
                itemsIndexed(quizPostings.posts) { index, post ->
                    if (index == 0)
                        HorizontalDivider()

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onPostClick(post.id)
                        }
                        .padding(10.dp)
                    ) {
                        Row(modifier = Modifier.padding(bottom = 10.dp)) {
                            Text(text = post.title, color = MaterialColors.deepOrangeA700)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = post.date, fontSize = 12.sp)
                            Text(
                                text = Repository.getCategoryNameById(post.category),
                                fontSize = 12.sp
                            )
                            Text(text = post.author, fontSize = 12.sp)
                        }
                    }

                    HorizontalDivider()
                }
            }
        }
    }
}


