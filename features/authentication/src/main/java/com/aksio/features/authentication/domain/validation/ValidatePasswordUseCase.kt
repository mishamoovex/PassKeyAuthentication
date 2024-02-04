package com.aksio.features.authentication.domain.validation

import com.aksio.core.common.constants.Constants
import com.aksio.core.common.state.TextMessage
import com.aksio.core.hilt.DispatcherComputation
import com.aksio.features.authentication.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(
    @DispatcherComputation private val computationDispatcher: CoroutineDispatcher,
) : StringValidationUseCase {

    override suspend fun invoke(value: String): TextMessage? {
        if (value.isEmpty() && value.isBlank()) return TextMessage.ResourceMessage(
            templateRes = R.string.text_validation_error_password_empty
        )
        if (value.length > Constants.PASSWORD_LENGTH) return TextMessage.ResourceMessage(
            templateRes = R.string.text_validation_error_password_too_long
        )

        val charValidation = charactersValidation(value)

        return if (charValidation.isEmpty()) {
            null
        } else {
            TextMessage.BuildString(
                base = R.string.text_validation_error_password_base,
                cases = charValidation
            )
        }
    }

    private suspend fun charactersValidation(password: String): List<Int> =
        withContext(computationDispatcher) {
            val errors = mutableListOf<Int>()
            if (!password.hasDigits()) errors.add(R.string.text_validation_error_password_base_digit)
            if (password.hasLetter()) {
                if (!password.hasUpperCase()) errors.add(R.string.text_validation_error_password_base_capitalized_letter)
            } else {
                errors.add(R.string.text_validation_error_password_base_letter)
            }
            errors
        }

    private fun String.hasUpperCase() = any { it.isUpperCase() }

    private fun String.hasDigits() = any { it.isDigit() }

    private fun String.hasLetter() = any { it.isLetter() }
}