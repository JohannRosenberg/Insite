package io.github.johannrosenberg.insite.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavMenuItem(
    id: String,
    icon: ImageVector,
    label: String,
    selected: Boolean = false,
    saveSelectedMenuItem: Boolean = false,
    composableResId: String,
    dstArgs: Any? = null,
    onNavItemClick: (menuId: String, saveSelectedMenuItem: Boolean, composableResId: String, dstArgs: Any?) -> Unit
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(end = 10.dp),
        label = { Text(label) },
        selected = selected,
        icon = {
            Icon(
                modifier = Modifier.size(ScreenGlobals.NAVIGATION_MENU_ICON_SIZE),
                imageVector = icon,
                contentDescription = "",
                tint = AppColors.navigationIconTint
            )
        },
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor = AppColors.navigationItemUnselectedContainerColor,
            selectedContainerColor = AppColors.navigationItemSelectedContainerColor
        ),
        shape = RoundedCornerShape(0.dp, 25.dp, 25.dp, 0.dp),
        onClick = {
            onNavItemClick(id, saveSelectedMenuItem, composableResId, dstArgs)
        })
}
