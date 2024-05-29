package io.github.johannrosenberg.insite.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R

data class CustomAlertDialogInfo(
    val message: String = "",
    val icon: ImageVector? = Icons.Filled.Info,
    val confirmButtonTextResId: Int = R.string.okay,
    val cancelButtonTextResId: Int = R.string.cancel,
    val onConfirmClick: (() -> Unit)? = null,
    val onCancelClick: (() -> Unit)? = null
) {
    fun show(): CustomAlertDialogInfo {
        App.mainViewModel.showAlertDialog.value = true
        return this
    }
}
