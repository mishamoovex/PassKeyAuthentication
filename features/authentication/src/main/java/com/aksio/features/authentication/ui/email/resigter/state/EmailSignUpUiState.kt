package com.aksio.features.authentication.ui.email.resigter.state

import com.aksio.core.common.state.TextFieldState

data class EmailSignUpUiState(
    val emailState: TextFieldState,
    val passwordState: TextFieldState,
    val passwordConfirmationState: TextFieldState,
    val isLoading: Boolean,
    val onSignUpClicked: () -> Unit
)
