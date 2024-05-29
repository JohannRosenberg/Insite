package io.github.johannrosenberg.insite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.theme.AppColors

@Composable
fun CustomListItem(
    icon: ImageVector,
    iconBackgroundColor: Color? = null,
    onClick: (() -> Unit)? = null,
    headlineContent: @Composable () -> Unit,
    supportingContent: @Composable () -> Unit,
    trailingContent: (@Composable () -> Unit)? = null
) {
    val modifier = if (onClick != null) {
        Modifier.clickable {
            onClick()
        }
    } else {
        Modifier
    }

    ListItem(
        modifier = modifier,
        headlineContent = headlineContent,
        leadingContent = {
            Icon(
                modifier = Modifier
                    .size(ScreenGlobals.LIST_ITEM_ICON_SIZE)
                    .run {
                        if (iconBackgroundColor != null) {
                            this.background(color = iconBackgroundColor, shape = CircleShape)
                        } else
                            this
                    }
                    .padding(5.dp),
                imageVector = icon,
                contentDescription = "",
                tint = if (iconBackgroundColor != null) AppColors.tintForIconsWithBackgroundColor else AppColors.tintForIconsWithNoBackgroundColor
            )
        },
        supportingContent = supportingContent,
        trailingContent = {
            trailingContent?.invoke()
        }
    )
}