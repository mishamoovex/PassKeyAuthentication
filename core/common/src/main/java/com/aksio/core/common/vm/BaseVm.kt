package com.aksio.core.common.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aksio.core.common.state.NavigationState
import com.aksio.core.common.state.SnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

abstract class BaseVm(
    private val handleInternetConnection: Boolean = false
) : ViewModel() {

    protected open var doOnError = {}

    private val coroutineErrorHandler = CoroutineExceptionHandler { _, throwable ->
        setMessage(SnackbarMessage.StringMessage(throwable.rationale))
        doOnError()
        throw throwable
    }

    protected val executionScope = viewModelScope + coroutineErrorHandler

    private val messageChannel = MutableStateFlow(
        NavigationState<SnackbarMessage>(
            event = null,
            onNavigated = { setMessage(null) }
        )
    )
    val messageEvent = messageChannel.asStateFlow()

    fun setMessage(message: SnackbarMessage?) {
        messageChannel.update {
            it.copy(event = message)
        }
    }

    private val Throwable.rationale: String
        get() = if (isNoInternetException() && handleInternetConnection) {
            "No Internet Connection"
        } else {
            message ?: "Unknown error"
        }

    private fun Throwable.isNoInternetException() =
        this is TimeoutException || this is UnknownHostException || this is ConnectException
}