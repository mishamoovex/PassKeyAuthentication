package com.aksio.features.authentication.ui.email.resigter.screen

import com.aksio.core.common.constants.Constants.PASSWORD_LENGTH
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aksio.core.common.core.messenger.error.ErrorMessenger
import com.aksio.core.common.state.ActionButtonState
import com.aksio.core.common.state.TextFieldState
import com.aksio.core.common.state.TextMessage
import com.aksio.core.common.state.ValidationState
import com.aksio.core.common.vm.executeAction
import com.aksio.features.authentication.R
import com.aksio.features.authentication.di.EmailValidator
import com.aksio.features.authentication.di.PasswordValidator
import com.aksio.features.authentication.domain.validation.StringValidationUseCase
import com.aksio.features.authentication.ui.email.resigter.state.EmailSignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EmailSignUpVm @Inject constructor(
    @EmailValidator private val validateEmailUseCase: StringValidationUseCase,
    @PasswordValidator private val validatePasswordUseCase: StringValidationUseCase,
    private val messenger: ErrorMessenger
) : ViewModel(),
    ErrorMessenger by messenger {

    private companion object {
        const val EMAIL_VALIDATION_LENGTH = 6
    }

    private val emailState = MutableStateFlow(
        TextFieldState(onValueChanged = ::updateEmail)
    )

    private fun updateEmail(email: String) {
        executeAction {
            val validationResult = if (email.length >= EMAIL_VALIDATION_LENGTH) {
                validateEmailUseCase(email)
                    ?.let(ValidationState::Invalid)
                    ?: ValidationState.Valid
            } else ValidationState.Pending

            emailState.update {
                it.copy(
                    value = email,
                    validationState = validationResult
                )
            }
        }
    }

    private val passwordState = MutableStateFlow(
        TextFieldState(onValueChanged = ::updatePassword)
    )

    private fun updatePassword(password: String) {
        executeAction {
            val validationResult = if (password.length >= PASSWORD_LENGTH) {
                validatePasswordUseCase(password)
                    ?.let(ValidationState::Invalid)
                    ?: ValidationState.Valid
            } else ValidationState.Pending

            passwordState.update {
                it.copy(
                    value = password,
                    validationState = validationResult
                )
            }
        }
    }

    private val confirmationPasswordState = MutableStateFlow(
        TextFieldState(onValueChanged = ::confirmPassword)
    )

    private fun confirmPassword(password: String) {
        confirmationPasswordState.update {
            it.copy(value = password)
        }
    }

    private val isLoadingState = MutableStateFlow(false)

    val uiState: StateFlow<EmailSignUpUiState> = combine(
        emailState,
        passwordState,
        confirmationPasswordState,
        isLoadingState
    ) { email, password, confirmationPassword, isLoading ->

        val updatedConfirmationPasswordState = confirmationPassword.copy(
            validationState = validateConfirmationPassword(
                password = password.value,
                confirmationPassword = confirmationPassword.value
            )
        )
        val isDataValid = email.validationState == ValidationState.Valid
                && password.validationState == ValidationState.Valid
                && updatedConfirmationPasswordState.validationState == ValidationState.Valid

        EmailSignUpUiState(
            emailState = email,
            passwordState = password,
            passwordConfirmationState = updatedConfirmationPasswordState,
            actionButtonState = ActionButtonState(
                isLoading = isLoading,
                isEnabled = isDataValid,
                onClicked = ::singUpWithEmail
            )
        )
    }
        .catch { showError(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = EmailSignUpUiState(
                emailState = emailState.value,
                passwordState = passwordState.value,
                passwordConfirmationState = confirmationPasswordState.value,
                actionButtonState = ActionButtonState()
            )
        )

    private fun validateConfirmationPassword(
        password: String,
        confirmationPassword: String
    ): ValidationState = if (confirmationPassword.length == password.length) {
        if (confirmationPassword == password) {
            ValidationState.Valid
        } else {
            ValidationState.Invalid(
                TextMessage.ResourceMessage(
                    templateRes = R.string.text_validation_error_password_confirmation
                )
            )
        }
    } else {
        ValidationState.Pending
    }

    private fun singUpWithEmail() {
        executeAction(
            onLoading = { isLoading -> isLoadingState.update { isLoading } },
            execute = { delay(1500) }
        )
    }

}