package io.github.johannrosenberg.insite.ui.components

import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R

data class ModalEditTextInfo(
    var title: String = "",
    var text: String = "",
    var required: Boolean = true,
    var confirmButtonLabel: String = App.context.getString(R.string.okay),
    var cancelButtonLabel: String = App.context.getString(R.string.cancel),
    var onConfirmClick:(editedText:String) -> Unit = {},
    var onCancelClick: (() -> Unit)? = null
) {
    fun show(): ModalEditTextInfo {
        App.mainViewModel.modalEditTextInfo =  this
        App.mainViewModel.modalEditTextVal.value = this.text
        App.mainViewModel.modalEditTextDialogVisible.value = true
        return  this
    }
}