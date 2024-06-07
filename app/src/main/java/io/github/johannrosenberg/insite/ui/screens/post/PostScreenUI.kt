package io.github.johannrosenberg.insite.ui.screens.post

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R
import io.github.johannrosenberg.insite.da.LineInfo
import io.github.johannrosenberg.insite.da.web.AUTHOR_PHOTO_PATH
import io.github.johannrosenberg.insite.models.Levels
import io.github.johannrosenberg.insite.models.PostDetails
import io.github.johannrosenberg.insite.ui.components.BackButton
import io.github.johannrosenberg.insite.ui.components.Markdown
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_FONT_SIZE
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_BOTTOM
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_END
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_TOP
import io.github.johannrosenberg.insite.ui.theme.AppColors
import io.github.johannrosenberg.insite.ui.theme.AppColors.challengeLevelEasyText
import io.github.johannrosenberg.insite.ui.theme.AppColors.challengeLevelHardText
import io.github.johannrosenberg.insite.ui.theme.AppColors.challengeLevelModerateText
import io.github.johannrosenberg.insite.ui.theme.MaterialColors
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
        label = App.context.getString(R.string.challenge),
        selectedIcon = Icons.Filled.Quiz,
        unselectedIcon = Icons.Outlined.Quiz
    ),
    Solution(
        id = "solution",
        label = App.context.getString(R.string.solution),
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List
    ),
    Author(
        id = "author",
        label = App.context.getString(R.string.author),
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
}

@Composable
fun PostHandler(composableInstance: ComposableInstance) {

    CompositionLocalProvider(LocalComposableInstance provides composableInstance) {

        val vm = composableInstance.viewmodel as PostViewModel
        val postId = composableInstance.parameters as String

        vm.getPostDetails(postId)

        fun launchUrlInBrowser(url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(App.context, intent, null)
        }

        PostScreen(
            postDetails = vm.postDetails.value,
            challenge = vm.challengee,
            solution = vm.solution,
            onBackButtonClick = {
                navman.goBack()
            },
            onLaunchDiscussion = { url ->
                launchUrlInBrowser(url = url)
            },
            onAuthorLink1Click = { url ->
                launchUrlInBrowser(url = url)
            },
            onAuthorLink2Click = { url ->
                launchUrlInBrowser(url = url)
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PostScreen(
    postDetails: PostDetails?,
    challenge: MutableList<LineInfo>,
    solution: MutableList<LineInfo>,
    onBackButtonClick: () -> Unit,
    onLaunchDiscussion: (discussionUrl: String) -> Unit,
    onAuthorLink1Click: (url: String) -> Unit,
    onAuthorLink2Click: (url: String) -> Unit,
) {
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
                        Text(App.context.getString(R.string.challenge), fontSize = APPBAR_FONT_SIZE, color = AppColors.toolbarTitle)
                    }
                },
                navigationIcon = {
                    Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                        BackButton(onBackButtonClick = onBackButtonClick)
                    }
                },
                actions = {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                        Text(
                            text = postDetails?.level.toString(), textAlign = TextAlign.End, color = MaterialColors.white,
                            modifier = Modifier
                                .wrapContentWidth()
                                .background(
                                    color =
                                    when (postDetails?.level) {
                                        Levels.EASY -> challengeLevelEasyText
                                        Levels.MODERATE -> challengeLevelModerateText
                                        else -> challengeLevelHardText
                                    },
                                    shape = RoundedCornerShape(5.dp)
                                ).padding(horizontal = 10.dp, vertical = 3.dp),
                            fontSize = 14.sp
                        )
                    }
                }
            )

            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                ) {
                    when (page) {
                        PostTabs.Challenge.ordinal -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Text(
                                    text = postDetails?.title ?: "",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(bottom = 20.dp)
                                )

                                Markdown(lines = challenge)

                            }
                        }

                        PostTabs.Solution.ordinal -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Markdown(lines = solution)

                                Spacer(modifier = Modifier.height(20.dp))

                                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Button(
                                        colors = AppColors.defaultButtonColors,
                                        onClick = {
                                            onLaunchDiscussion(postDetails?.discussionUrl ?: "")
                                        }) {
                                        Icon(Icons.Outlined.Chat, contentDescription = null)
                                        Text("Discussion", modifier = Modifier.padding(start = 10.dp), fontSize = 18.sp, color = MaterialColors.white)
                                    }
                                }
                            }
                        }

                        PostTabs.Author.ordinal -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp),
                                    model = AUTHOR_PHOTO_PATH + postDetails?.author?.photo,
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth
                                )
                                Text(
                                    text = postDetails?.author?.name ?: "",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(bottom = 20.dp)
                                )
                                Text(
                                    text = postDetails?.author?.bio ?: "",
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(bottom = 30.dp)
                                )

                                if (postDetails?.author?.url1 != null) {
                                    Text(
                                        text = postDetails.author.url1,
                                        color = MaterialColors.lightBlue300,
                                        fontSize = 14.sp,
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier
                                            .padding(bottom = 20.dp)
                                            .clickable {
                                                onAuthorLink1Click(postDetails.author.url1)
                                            }
                                    )
                                }

                                if (postDetails?.author?.url2 != null) {
                                    Text(
                                        text = postDetails.author.url2,
                                        color = MaterialColors.lightBlue300,
                                        fontSize = 14.sp,
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier
                                            .padding(bottom = 30.dp)
                                            .clickable {
                                                onAuthorLink2Click(postDetails.author.url2)
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            TabRow(selectedTabIndex = selectedTabIndex.value,
                indicator = { tabPositions ->
                    if (selectedTabIndex.value < tabPositions.size) {
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value]),
                            color = AppColors.bottomTabIndicator
                        )
                    }
                }) {
                PostTabs.entries.forEachIndexed { index, currentTab ->
                    Tab(selected = selectedTabIndex.value == index,
                        selectedContentColor = AppColors.bottomTabIconTextSelected,
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

