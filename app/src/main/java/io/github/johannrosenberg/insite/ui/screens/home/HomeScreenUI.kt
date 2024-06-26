package io.github.johannrosenberg.insite.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R
import io.github.johannrosenberg.insite.da.Repository
import io.github.johannrosenberg.insite.models.Post
import io.github.johannrosenberg.insite.models.QuizPostings
import io.github.johannrosenberg.insite.ui.components.PostHeader
import io.github.johannrosenberg.insite.ui.nav.NavMenuConstants.NAV_MENU_ID_SHOW_ALL_POSTS
import io.github.johannrosenberg.insite.ui.screens.ComposableResourceIDs
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_FONT_SIZE
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_ICON_SIZE
import io.github.johannrosenberg.insite.ui.screens.main.MainViewModel
import io.github.johannrosenberg.insite.ui.theme.AppColors
import io.github.johannrosenberg.insite.ui.theme.MaterialColors
import io.github.johannrosenberg.jetmagic.models.ComposableInstance
import io.github.johannrosenberg.jetmagic.models.LocalComposableInstance
import io.github.johannrosenberg.jetmagic.navigation.navman
import kotlinx.coroutines.launch

@Composable
fun HomeHandler(composableInstance: ComposableInstance) {

    CompositionLocalProvider(LocalComposableInstance provides composableInstance) {

        val coroutineScope = rememberCoroutineScope()
        val vmMain: MainViewModel = viewModel()

        HomeScreen(
            quizPostings = Repository.quizPostings.value,
            categoryId = Repository.selectedCategoryId.value,
            showFilterButton = Repository.isASubCategoryOrHasSubCategories(Repository.selectedCategoryId.value),
            onNavMenuButtonClick = {
                coroutineScope.launch {
                    vmMain.drawerState.open()
                }
            },
            onPostClick = { post ->
                navman.goto(composableResId = ComposableResourceIDs.POST_SCREEN, p = post)
            },
            onFilterBySubCategoryClick = { categoryId ->
                Repository.saveSelectedCategoryId(categoryId)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    quizPostings: QuizPostings,
    categoryId: String,
    showFilterButton: Boolean,
    onNavMenuButtonClick: () -> Unit,
    onPostClick: (post: Post) -> Unit,
    onFilterBySubCategoryClick: (categoryId: String) -> Unit
) {
    var showFilterMenu by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
            /*                .paint(
                                painter = rememberAsyncImagePainter(
                                    IMAGES_PATH + Repository
                                        .getRootCategoryId(categoryId)
                                        .lowercase() + ".jpg"
                                ),
                                contentScale = ContentScale.FillBounds,
                                alpha = 0.25f
                            )*/
        ) {
            TopAppBar(
                modifier = Modifier
                    .height(ScreenGlobals.DEFAULT_APPBAR_HEIGHT),
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val title =
                            if (categoryId == NAV_MENU_ID_SHOW_ALL_POSTS)
                                App.context.getString(R.string.all_categoies)
                            else Repository.getCategoryNameById(categoryId)

                        Text(
                            text = title,
                            fontSize = APPBAR_FONT_SIZE,
                            color = AppColors.toolbarTitle
                        )
                    }
                },
                navigationIcon = {
                    Row(
                        Modifier.height(ScreenGlobals.DEFAULT_APPBAR_HEIGHT),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onNavMenuButtonClick) {
                            Icon(
                                modifier = Modifier.size(APPBAR_ICON_SIZE),
                                tint = AppColors.toolbarNavIcon,
                                imageVector = ImageVector.vectorResource(R.drawable.menu),
                                contentDescription = ""
                            )
                        }
                    }
                },
                actions = {
                    Row(
                        Modifier.height(ScreenGlobals.DEFAULT_APPBAR_HEIGHT),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (showFilterButton) {
                            Box(
                                modifier = Modifier
                                    .wrapContentSize(Alignment.TopStart)
                            ) {
                                IconButton(onClick = {
                                    showFilterMenu = true
                                }) {
                                    Icon(
                                        modifier = Modifier.size(APPBAR_ICON_SIZE),
                                        tint = MaterialColors.tealA100,
                                        imageVector = Icons.Filled.FilterList,
                                        contentDescription = ""
                                    )
                                }

                                DropdownMenu(
                                    modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer),
                                    expanded = showFilterMenu,
                                    onDismissRequest = { showFilterMenu = false }
                                ) {
                                    val subCategories = Repository.getSubCategories(categoryId = categoryId)

                                    DropdownMenuItem(
                                        text = { Text(App.context.getString(R.string.show_all_posts)) },
                                        onClick = {
                                            showFilterMenu = false
                                            onFilterBySubCategoryClick("")
                                        },
                                    )

                                    subCategories?.forEach { subCategory ->
                                        DropdownMenuItem(
                                            text = { Text(subCategory.name) },
                                            onClick = {
                                                showFilterMenu = false
                                                onFilterBySubCategoryClick(subCategory.id)
                                            },
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            )

            LazyColumn {
                itemsIndexed(quizPostings.posts) { _, post ->
                    var showPost = true

                    if (showFilterButton) {
                        showPost = (Repository.postBelongsToCategory(post.category, categoryId))
                    }

                    if (showPost) {
                        PostHeader(
                            post = post,
                            onPostClick = onPostClick
                        )

                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        }
    }
}


