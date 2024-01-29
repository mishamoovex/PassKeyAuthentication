package com.aksio.features.authentication.ui.email.resigter.screen

import com.aksio.core.common.state.TextFieldState
import com.aksio.core.common.vm.BaseVm
import com.aksio.features.authentication.domain.email.ValidateEmailUseCase
import com.aksio.features.authentication.domain.password.ValidatePasswordUseCase
import com.aksio.features.authentication.ui.email.resigter.state.EmailSignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailSignUpVm @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : BaseVm() {

    private val emailState = MutableStateFlow(
        TextFieldState(onValueChanged = ::updateEmail)
    )

    private fun updateEmail(email: String) {
        executionScope.launch {

        }
    }

    private val passwordState = MutableStateFlow(
        TextFieldState(onValueChanged = ::updatePassword)
    )

    private fun updatePassword(password: String) {

    }

    private val passwordConfirmationState = MutableStateFlow(
        TextFieldState(onValueChanged = ::confirmPassword)
    )

    private fun confirmPassword(password: String) {

    }

    private val isLoadingState = MutableStateFlow(false)

    val uiState: StateFlow<EmailSignUpUiState> = combine(
        emailState,
        passwordState,
        passwordConfirmationState,
        isLoadingState
    ) { email, password, passwordConfirmation, isLoading ->
        EmailSignUpUiState(
            emailState = email,
            passwordState = password,
            passwordConfirmationState = passwordConfirmation,
            isLoading = isLoading,
            onSignUpClicked = ::singUpWithEmail
        )
    }.stateIn(
        scope = executionScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = EmailSignUpUiState(
            emailState.value,
            passwordState.value,
            passwordConfirmationState.value,
            isLoadingState.value,
            onSignUpClicked = ::singUpWithEmail
        )
    )

    private fun singUpWithEmail() {

    }
}