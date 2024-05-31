package io.github.johannrosenberg.insite.models

import io.github.johannrosenberg.insite.ui.nav.NavMenuConstants.NAV_MENU_ID_SHOW_ALL_POSTS
import kotlinx.serialization.Serializable

/**
 * All the settings and configuration data are stored in this class.
 */
@Serializable
class AppData {
    /**
     * The version number of this data format.
     */
    val version: Int = 1

    var selectedNavMenuId: String = NAV_MENU_ID_SHOW_ALL_POSTS
    var selectedCategoryId: String = ""

}
