package com.example.utils.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.event.SnackbarAction
import com.example.utils.event.SnackbarController
import com.example.utils.event.SnackbarEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _exitChanel = Channel<Unit>()
    val exitChanel: ReceiveChannel<Unit> = _exitChanel

    protected fun showSnackbar(
        message: String,
        action: SnackbarAction? = null
    ) {
        viewModelScope.launch {
            SnackbarController.sendEvent(
                event = SnackbarEvent(
                    message = message,
                    snackbarAction = action
                )
            )
        }
    }

    protected suspend fun goBack() {
        _exitChanel.send(Unit)
    }

}