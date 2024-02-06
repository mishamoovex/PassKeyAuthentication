package com.aksio.features.authentication.ui.email.resigter.state

import com.aksio.core.common.state.ActionButtonState
import com.aksio.core.common.state.NavigationState
import com.aksio.core.common.state.TextFieldState

data class EmailSignUpUiState(
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val passwordConfirmationState: TextFieldState = TextFieldState(),
    val actionButtonState: ActionButtonState = ActionButtonState(),
    val navigationState: NavigationState<Unit> = NavigationState()
)
