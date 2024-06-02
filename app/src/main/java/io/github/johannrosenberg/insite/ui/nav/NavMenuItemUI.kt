package io.github.johannrosenberg.insite.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterListOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.theme.AppColors

@Composable
fun NavMenuItem(
    id: String,
    iconPath: String? = null,
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
            if (iconPath == null) {
                Icon(
                    modifier = Modifier.size(ScreenGlobals.NAVIGATION_MENU_ICON_SIZE),
                    imageVector = Icons.Filled.FilterListOff,
                    contentDescription = "",
                    tint = AppColors.navigationIconTint
                )
            } else {
                AsyncImage(
                    modifier = Modifier.size(ScreenGlobals.NAVIGATION_MENU_ICON_SIZE),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(iconPath)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = null, colorFilter = ColorFilter.tint(color = AppColors.navigationIconTint)
                )
            }
        },
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor = MaterialTheme.colorScheme.surfaceTint,
            selectedContainerColor = AppColors.navigationItemSelectedContainerColor,
            selectedTextColor = AppColors.navigationItemSelectedTextColor,
        ),
        shape = RoundedCornerShape(0.dp, 25.dp, 25.dp, 0.dp),
        onClick = {
            onNavItemClick(id, saveSelectedMenuItem, composableResId, dstArgs)
        })
}

