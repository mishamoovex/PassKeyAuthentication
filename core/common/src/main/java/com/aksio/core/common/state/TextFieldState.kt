package com.aksio.core.common.state

data class TextFieldState(
    val value: String = "",
    val validationState: ValidationState = ValidationState.Pending,
    val onValueChanged: (String) -> Unit
)

sealed class ValidationState {

    data object Pending : ValidationState()

    data object Valid : ValidationState()

    data class Invalid(val errorMessage: TextMessage) : ValidationState()
}