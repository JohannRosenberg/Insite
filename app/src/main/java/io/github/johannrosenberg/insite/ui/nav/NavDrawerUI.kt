package io.github.johannrosenberg.insite.ui.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R
import io.github.johannrosenberg.insite.da.Repository
import io.github.johannrosenberg.insite.da.web.NAV_MENU_ICON_PATH
import io.github.johannrosenberg.insite.models.QuizPostings
import io.github.johannrosenberg.insite.ui.nav.NavMenuConstants.NAV_MENU_ID_SHOW_ALL_POSTS
import io.github.johannrosenberg.insite.ui.screens.ComposableResourceIDs
import io.github.johannrosenberg.insite.ui.theme.MaterialColors
import kotlinx.coroutines.launch


@Composable
fun NavDrawerHandler(drawerState: DrawerState, modifier: Modifier = Modifier) {

    val vm: NavDrawerViewModel = viewModel()
    App.mainViewModel.onNavigationDrawerOpened.intValue

    val scrollState = vm.navDrawerScrollState
    val coroutineScope = rememberCoroutineScope()

    NavDrawer(
        quizPostings = Repository.quizPostings.value,
        currentMenuId = Repository.selectedNavMenuId.value,
        scrollState,
        onNavItemClick = { menuId, saveSelectedMenuItem, composableResId, screenData ->
            coroutineScope.launch {
                drawerState.close()
                vm.onNavItemClick(
                    menuId = menuId,
                    saveMenuId = saveSelectedMenuItem,
                    composableResId = composableResId,
                    p = screenData
                )
            }
        },
        modifier = modifier
    )
}


@Composable
fun NavDrawer(
    quizPostings: QuizPostings,
    currentMenuId: String,
    navDrawerScrollState: ScrollState,
    onNavItemClick: (menuId: String, saveSelectedMenuItem: Boolean, composableResId: String, screenData: Any?) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surfaceTint)
                .wrapContentWidth()
                .fillMaxHeight()
                .verticalScroll(navDrawerScrollState)
        ) {
            ListItem(
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                headlineContent = {
                    Text(
                        App.context.getString(R.string.app_name),
                        color = MaterialColors.cyan300,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                leadingContent = {
                    Image(
                        modifier = Modifier.size(70.dp),
                        painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = ""
                    )
                },
                supportingContent = { Text(App.context.getString(R.string.challenges_for_critical_thinkers)) },
                colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceTint)
            )

            HorizontalDivider()
            NavMenuItem(
                id = NAV_MENU_ID_SHOW_ALL_POSTS,
                label = App.context.getString(R.string.show_all_posts),
                selected = currentMenuId == NAV_MENU_ID_SHOW_ALL_POSTS,
                saveSelectedMenuItem = true,
                composableResId = ComposableResourceIDs.HOME,
                onNavItemClick = onNavItemClick
            )
            HorizontalDivider()

            quizPostings.categories.forEach {
                NavMenuItem(
                    id = it.id,
                    iconPath = NAV_MENU_ICON_PATH + it.name.lowercase() + ".svg",
                    label = it.name,
                    selected = currentMenuId == it.id,
                    saveSelectedMenuItem = true,
                    composableResId = ComposableResourceIDs.HOME,
                    onNavItemClick = onNavItemClick
                )
            }
        }
    }
}