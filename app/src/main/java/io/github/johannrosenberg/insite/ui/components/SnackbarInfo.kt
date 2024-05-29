package io.github.johannrosenberg.insite.ui.components

data class SnackbarInfo (
    val message: String = "",
    val isCritical: Boolean = false,
    val showProgressIndicator: Boolean = false,
    val actionLabel: String = "",
    val actionCallback: (suspend () -> Unit)? = null
)