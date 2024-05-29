package io.github.johannrosenberg.insite.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R
import io.github.johannrosenberg.insite.ui.screens.main.MainViewModel
import io.github.johannrosenberg.insite.ui.theme.MaterialColors

@Composable
fun ProgressScreenHandler() {
    val vmMain: MainViewModel = viewModel()
    val visible = vmMain.progressScreenVisible.value

    ProgressScreen(
        text = vmMain.progressScreenText,
        visible = visible,
        onDismiss = {
            vmMain.dismissProgressScreen()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(
    text: String,
    visible: Boolean,
    onDismiss: () -> Unit
) {
    if (visible) {
        BasicAlertDialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Surface(
                modifier = Modifier.wrapContentSize(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement
                        .Center
                ) {
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(30.dp), color = MaterialColors.gray800)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text, color = MaterialColors.gray800)
                    }

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(
                            onClick = onDismiss
                        ) {
                            Text(App.context.getString(R.string.cancel))
                        }
                    }
                }
            }
        }
    }
}