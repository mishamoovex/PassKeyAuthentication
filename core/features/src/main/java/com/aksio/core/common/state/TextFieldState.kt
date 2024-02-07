package com.aksio.core.common.state

import android.content.Context

data class TextFieldState(
    val value: String = "",
    val validationState: ValidationState = ValidationState.Pending,
    val onValueChanged: (String) -> Unit = {}
) {
    fun getErrorMessage(context: Context): String? =
        if (validationState is ValidationState.Invalid) {
            validationState.errorMessage.asDisplayText(context)
        } else null

    fun isError(): Boolean = validationState is ValidationState.Invalid
}

sealed class ValidationState {

    data object Pending : ValidationState()

    data object Valid : ValidationState()

    data class Invalid(val errorMessage: TextMessage) : ValidationState()
}