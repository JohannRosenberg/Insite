package io.github.johannrosenberg.insite.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals

@Composable
fun BackButton(
    onBackButtonClick: () -> Unit
){
    IconButton(onClick = onBackButtonClick) {
        Icon(
            modifier = Modifier
                .size(ScreenGlobals.BACK_BUTTON_ICON_SIZE ),
            tint = MaterialTheme.colorScheme.primary,
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = ""
        )
    }
}