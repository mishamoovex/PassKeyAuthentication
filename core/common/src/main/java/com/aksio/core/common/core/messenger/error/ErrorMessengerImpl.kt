package com.aksio.core.common.core.messenger.error

import com.aksio.core.common.core.messenger.base.Messenger
import com.aksio.core.common.state.TextMessage
import javax.inject.Inject

class ErrorMessengerImpl @Inject constructor(
    private val baseMessenger: Messenger
) : ErrorMessenger,
    Messenger by baseMessenger {

    override fun showError(throwable: Throwable) {
        getErrorMessage(throwable).run(::showMessage)
    }

    //The complex error generation is omitted for simplicity
    private fun getErrorMessage(throwable: Throwable): TextMessage =
        TextMessage.StringMessage(throwable.rationale)

    private val Throwable.rationale: String
        get() = message ?: "Unknown error"
}