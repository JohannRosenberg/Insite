package io.github.johannrosenberg.insite.ui.nav

//import io.github.johannrosenberg.catlaser.da.Repository
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import io.github.johannrosenberg.insite.ui.theme.MaterialColors
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawerHandler(drawerState: DrawerState, modifier: Modifier = Modifier) {

    val vm: NavDrawerViewModel = viewModel()
    App.mainViewModel.onNavigationDrawerOpened.value

    val scrollState = vm.navDrawerScrollState
    val coroutineScope = rememberCoroutineScope()

    NavDrawer(
        //currentMenuId.value,
        //Repository.appData.motionConfigs,
        currentMenuId = vm.currentMenuId.value,
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(
    //motionConfigs: List<MotionConfig>,
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
                .background(MaterialTheme.colorScheme.surface)
                .wrapContentWidth()
                .fillMaxHeight()
                .verticalScroll(navDrawerScrollState)
        ) {
            ListItem(
                modifier = Modifier.padding(top = 10.dp),
                headlineContent = {
                    Text(
                        App.context.getString(R.string.app_name),
                        color = MaterialColors.red300,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                leadingContent = {
                    Image(
                        modifier = Modifier
                            .size(40.dp),
                        //.background(color = AppColors.settingsIconBackground, shape = CircleShape),
                        painter = painterResource(id = R.drawable.thinking_emoji),
                        contentDescription = ""
                    )
                },
                supportingContent = { Text(App.context.getString(R.string.for_critical_thinkers)) },
                colors = ListItemDefaults.colors(containerColor = MaterialColors.white)
            )

            Divider(modifier = Modifier.padding(vertical = 10.dp))

            /*            motionConfigs.forEach {
                            if (it.fullScreenMode || (!it.fullScreenMode && servoControllerIsAvailable)) {
                                NavMenuItem(
                                    id = it.id,
                                    icon = Icons.Filled.TurnSharpRight,
                                    label = it.name,
                                    selected = currentMenuId == it.id,
                                    saveSelectedMenuItem = true,
                                    composableResId = ComposableResourceIDs.HOME,
                                    onNavItemClick = onNavItemClick
                                )
                            }
                        }*/


        }
    }
}