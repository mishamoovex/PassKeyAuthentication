package com.aksio.core.common.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aksio.core.common.core.messenger.error.ErrorMessenger
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun <T> T.executeAction(
    onLoading: ((Boolean) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    execute: suspend CoroutineScope.() -> Unit
): Job where T : ViewModel, T : ErrorMessenger {
    onLoading?.invoke(true)

    return viewModelScope.launch {
        try {
            execute()
        } catch (t: Throwable) {
            if (t is CancellationException) throw t
            onError?.invoke(t) ?: showError(t)
        } finally {
            onLoading?.invoke(false)
        }
    }
}