package io.github.johannrosenberg.insite.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R
import io.github.johannrosenberg.insite.ui.screens.main.MainViewModel


@Composable
fun CustomAlertDialogHandler() {
    val vmMain: MainViewModel = viewModel()

    CustomAlertDialog(
        displayDialog = vmMain.showAlertDialog.value,
        message = vmMain.alertDialogInfo.message,
        icon = vmMain.alertDialogInfo.icon,
        confirmButtonTextResId = vmMain.alertDialogInfo.confirmButtonTextResId,
        cancelButtonTextResId = vmMain.alertDialogInfo.cancelButtonTextResId,
        onConfirmClick = vmMain.alertDialogInfo.onConfirmClick,
        onCancelClick = {
            vmMain.showAlertDialog.value = false
            vmMain.alertDialogInfo.onCancelClick?.invoke()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    displayDialog: Boolean,
    message: String,
    icon: ImageVector? = Icons.Filled.Info,
    confirmButtonTextResId: Int = R.string.okay,
    cancelButtonTextResId: Int = R.string.cancel,
    onConfirmClick: (() -> Unit)? = null,
    onCancelClick: (() -> Unit)? = null
) {

    if (displayDialog) {
        BasicAlertDialog(
            onDismissRequest = { onCancelClick?.invoke() }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (icon != null) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.CenterHorizontally),
                            tint = MaterialTheme.colorScheme.primary,
                            imageVector = icon,
                            contentDescription = ""
                        )
                    }

                    Text(
                        text = message,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(
                            onClick = {
                                onCancelClick?.invoke()
                            },
                        ) {
                            Text(App.context.getString(cancelButtonTextResId))
                        }

                        if (onConfirmClick != null) {
                            Spacer(modifier = Modifier.height(24.dp))
                            TextButton(
                                onClick = {
                                    onConfirmClick()
                                },
                            ) {
                                Text(App.context.getString(confirmButtonTextResId))
                            }
                        }
                    }
                }
            }
        }
    }
}