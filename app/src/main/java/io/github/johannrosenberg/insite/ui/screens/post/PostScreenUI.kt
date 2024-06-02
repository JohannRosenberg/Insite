package io.github.johannrosenberg.insite.ui.screens.post

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.johannrosenberg.insite.ui.components.BackButton
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_FONT_SIZE
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_BOTTOM
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_END
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_TOP
import io.github.johannrosenberg.jetmagic.models.ComposableInstance
import io.github.johannrosenberg.jetmagic.models.LocalComposableInstance
import io.github.johannrosenberg.jetmagic.navigation.navman
import kotlinx.coroutines.launch

enum class PostTabs(
    val id: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    Challenge(
        id = "challenge",
        label = "Challenge",
        selectedIcon = Icons.Filled.Quiz,
        unselectedIcon = Icons.Outlined.Quiz
    ),
    Solution(
        id = "solution",
        label = "Solution",
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List
    ),
    Discussion(
        id = "discussion",
        label = "Discussion",
        selectedIcon = Icons.Filled.Chat,
        unselectedIcon = Icons.Outlined.Chat
    )
}

@Composable
fun PostHandler(composableInstance: ComposableInstance) {

    CompositionLocalProvider(LocalComposableInstance provides composableInstance) {

        val vm = composableInstance.viewmodel as PostViewModel
        val postId = composableInstance.parameters

        PostScreen(
            onBackButtonClick = {
                navman.goBack()
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PostScreen(
    onBackButtonClick: () -> Unit,
) {
    //var tabState by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { PostTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
        ) {
            TopAppBar(
                modifier = Modifier
                    .height(ScreenGlobals.DEFAULT_APPBAR_HEIGHT)
                    .padding(top = APPBAR_PADDING_TOP, end = APPBAR_PADDING_END, bottom = APPBAR_PADDING_BOTTOM),
                title = {
                    Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                        Text("Some Post", fontSize = APPBAR_FONT_SIZE, color = MaterialTheme.colorScheme.primary)
                    }
                },
                navigationIcon = {
                    Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                        BackButton(onBackButtonClick = onBackButtonClick)
                    }
                }
            )

            HorizontalPager(state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = PostTabs.entries[selectedTabIndex.value].label)
                }
            }

            /*            Column(modifier = Modifier.weight(1f)) {
                            //Text(text = "Content goes here")

                        }*/

            TabRow(selectedTabIndex = selectedTabIndex.value) {
                PostTabs.entries.forEachIndexed { index, currentTab ->
                    Tab(selected = selectedTabIndex.value == index,
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.outline,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentTab.ordinal)
                            }
                        },
                        text = { Text(text = currentTab.label) },
                        icon = {
                            Icon(
                                imageVector = if (selectedTabIndex.value == index) currentTab.selectedIcon else currentTab.unselectedIcon,
                                contentDescription = currentTab.label
                            )
                        }
                    )
                }
            }
        }
    }
}

