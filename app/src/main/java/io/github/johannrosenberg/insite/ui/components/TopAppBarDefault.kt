package io.github.johannrosenberg.insite.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_FONT_SIZE
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_BOTTOM
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_END
import io.github.johannrosenberg.insite.ui.screens.ScreenGlobals.APPBAR_PADDING_TOP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDefault(
    title: String,
    onBackButtonClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .height(ScreenGlobals.DEFAULT_APPBAR_HEIGHT)
            .padding(top = APPBAR_PADDING_TOP, end = APPBAR_PADDING_END, bottom = APPBAR_PADDING_BOTTOM),
        title = {
            Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Text(title, fontSize = APPBAR_FONT_SIZE, color = MaterialTheme.colorScheme.primary)
            }
        },
        navigationIcon = {
            Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                BackButton(onBackButtonClick = onBackButtonClick)
            }
        }
    )
}