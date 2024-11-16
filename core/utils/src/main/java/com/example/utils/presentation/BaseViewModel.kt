package com.example.utils.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.AndroidLogger
import com.example.common.AndroidResources
import com.example.common.Core
import com.example.common.CoreProvider
import com.example.utils.event.SnackbarAction
import com.example.utils.event.SnackbarController
import com.example.utils.event.SnackbarEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
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

    protected val viewModelScope: CoroutineScope by lazy {
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Core.errorHandler.handleError(exception)
        }
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate + errorHandler)
    }

    protected val resources: AndroidResources get() = Core.resources

    protected val logger: AndroidLogger get() = Core.logger

    protected suspend fun goBack() {
        _exitChanel.send(Unit)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}