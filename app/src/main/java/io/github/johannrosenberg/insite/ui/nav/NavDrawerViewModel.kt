package io.github.johannrosenberg.insite.ui.nav

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.github.johannrosenberg.insite.da.Repository
import io.github.johannrosenberg.jetmagic.navigation.navman

class NavDrawerViewModel : ViewModel() {

    var navDrawerScrollState: ScrollState = ScrollState(0)
    val currentMenuId = mutableStateOf(Repository.appData.selectedNavMenuId)

    init {
        navman.observeScreenChange {
/*            if (navman.totalScreensDisplayed == 1) {
                currentMenuId.value = Repository.appData.selectedMotionConfigId
            }*/
        }
    }

    fun onNavItemClick(menuId: String, saveMenuId: Boolean, composableResId: String, p: Any? = null) {
        Repository.saveSelectedNavMenuItemId(menuId)

        /*if (saveMenuId)
            currentMenuId.value = menuId

        when (true) {
            menuId.startsWith(MOTION_CONFIG_ID_PREFIX) -> {
                Repository.appData.selectedMotionConfigId = menuId
                Repository.saveAppData()
                App.motionConfigUpdated()
                navman.gotoHomeScreen()
            }

            (menuId == ADD_MOTION_CONFIG) -> {
                App.mainViewModel.modalEditTextInfo = ModalEditTextInfo(
                    title = App.context.getString(R.string.configuration_name),
                    onConfirmClick = { text ->
                        val id = MOTION_CONFIG_ID_PREFIX + text + "-" + (0..1_000_000).random().toString()
                        currentMenuId.value = id
                        val motionConfig = MotionConfig(id = id, name = text)
                        Repository.appData.motionConfigs.add(motionConfig)
                        Repository.appData.selectedMotionConfigId = id
                        Repository.saveAppData()
                        App.motionConfigUpdated()
                        navman.gotoHomeScreen()
                    }
                ).show()
            }

            else -> {
                navman.goto(composableResId = composableResId, p = p)
            }
        }*/
    }
}

