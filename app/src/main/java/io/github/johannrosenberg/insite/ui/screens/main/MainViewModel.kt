package io.github.johannrosenberg.insite.ui.screens.main

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.johannrosenberg.insite.ui.components.CustomAlertDialogInfo
import io.github.johannrosenberg.insite.ui.components.ModalEditTextInfo
import io.github.johannrosenberg.insite.ui.components.SnackbarInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var onNavigationDrawerOpened = mutableIntStateOf(0)

    var drawerState: DrawerState = DrawerState(initialValue = DrawerValue.Closed) {
        if (it == DrawerValue.Open) {
            onNavigationDrawerOpened.intValue = (0..1_000_000).random()
        }

        true
    }
    var snackbarInfo = SnackbarInfo()
    var alertDialogInfo = CustomAlertDialogInfo()

    var modalEditTextInfo = ModalEditTextInfo()
    val modalEditTextDialogVisible = mutableStateOf(false)
    var modalEditTextVal = mutableStateOf(modalEditTextInfo.text)

    private var _onSnackbarVisible = MutableLiveData(false)
    var onSnackbarVisible: LiveData<Boolean> = _onSnackbarVisible

    var progressScreenVisible = mutableStateOf(false)
    var progressScreenText = ""
    private var onProgressScreenDismissed: (() -> Unit)? = null

    var drawerGesturesEnabled = mutableStateOf(true)

    val showAlertDialog = mutableStateOf(false)

    fun displayProgressScreen(text: String, onProgressScreenDismissed: (() -> Unit)?) {
        this.onProgressScreenDismissed = onProgressScreenDismissed

        viewModelScope.launch(context = Dispatchers.Main) {
            progressScreenText = text
            progressScreenVisible.value = true
        }
    }

    fun dismissProgressScreen() {
        progressScreenVisible.value = false

        if (onProgressScreenDismissed != null) {
            onProgressScreenDismissed?.invoke()
        }
    }

    fun displaySnackbar(sbInfo: SnackbarInfo) {
        viewModelScope.launch(context = Dispatchers.Main) {
            snackbarInfo = sbInfo
            _onSnackbarVisible.value = true
        }
    }

    fun onSnackbarActionButtonClick() {
        _onSnackbarVisible.value = false

        viewModelScope.launch {
            delay(200)
            snackbarInfo.actionCallback?.invoke()
        }
    }

    fun hideSnackbar() {
        _onSnackbarVisible.value = false
    }
}