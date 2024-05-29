package io.github.johannrosenberg.insite.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.johannrosenberg.insite.ui.screens.main.MainViewModel


@Composable
fun ModalEditTextHandler() {
    val vmMain: MainViewModel = viewModel()

    ModalEditText(
        displayDialog = vmMain.modalEditTextDialogVisible.value,
        text = vmMain.modalEditTextVal.value,
        modalInfo = vmMain.modalEditTextInfo,
        onEditChange = { updatedText ->
            vmMain.modalEditTextVal.value = updatedText
        },
        onConfirm = { text ->
            vmMain.modalEditTextDialogVisible.value = false
            vmMain.modalEditTextInfo.onConfirmClick(text)
        },
        onCancelClick = {
            vmMain.modalEditTextDialogVisible.value = false
            vmMain.modalEditTextInfo.onCancelClick?.invoke()
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalEditText(
    displayDialog: Boolean,
    text: String,
    modalInfo: ModalEditTextInfo,
    onEditChange: (updatedText: String) -> Unit,
    onConfirm: (text: String) -> Unit,
    onCancelClick: () -> Unit
) {


    if (displayDialog) {
        val focusRequester = remember { FocusRequester() }
        val textFieldValue = remember { mutableStateOf(TextFieldValue(text, TextRange(text.length))) }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        BasicAlertDialog(
            onDismissRequest = {
            }
        ) {
            Surface(
                modifier = Modifier.wrapContentSize(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = modalInfo.title, modifier = Modifier.padding(bottom = 20.dp))

                    TextField(
                        value = textFieldValue.value,
                        onValueChange = {
                            textFieldValue.value = it
                            onEditChange(it.text.trim())
                        },
                        singleLine = true,
                        modifier = Modifier.focusRequester(focusRequester)
                    )

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(
                            onClick = onCancelClick,
                        ) {
                            Text(modalInfo.cancelButtonLabel)
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        TextButton(
                            enabled = text.trim().isNotEmpty(),
                            onClick = {
                                onConfirm(text.trim())
                            }
                        ) {
                            Text(modalInfo.confirmButtonLabel)
                        }
                    }
                }
            }
        }
    }
}