package io.github.johannrosenberg.insite.ui.nav

import androidx.compose.foundation.ScrollState
import androidx.lifecycle.ViewModel
import io.github.johannrosenberg.insite.da.Repository

class NavDrawerViewModel : ViewModel() {

    var navDrawerScrollState: ScrollState = ScrollState(0)

    fun onNavItemClick(menuId: String, saveMenuId: Boolean, composableResId: String, p: Any? = null) {
        Repository.saveSelectedNavMenuItemId(menuId)
    }
}

