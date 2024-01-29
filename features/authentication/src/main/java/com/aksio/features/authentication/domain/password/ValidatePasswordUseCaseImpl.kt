package com.aksio.features.authentication.domain.password

import javax.inject.Inject

class ValidatePasswordUseCaseImpl @Inject constructor() : ValidatePasswordUseCase {

    private companion object {
        const val PASSWORD_LENGTH = 6
    }

    override suspend fun invoke(password: String): List<PasswordValidationError> {
        if (password.isEmpty() && password.isBlank()) return listOf(PasswordValidationError.EMPTY)
        if (password.length > PASSWORD_LENGTH) return listOf(PasswordValidationError.TOO_LONG)
        return charactersValidation(password)
    }

    private fun charactersValidation(password: String): List<PasswordValidationError> {
        val errors = mutableListOf<PasswordValidationError>()
        if (!password.hasDigits()) errors.add(PasswordValidationError.NO_DIGITS)
        if (password.hasLetter()) {
            if (!password.hasUpperCase()) errors.add(PasswordValidationError.NO_CAPITALIZED_LETTER)
        } else {
            errors.add(PasswordValidationError.NO_LETTERS)
        }
        return errors
    }

    private fun String.hasUpperCase() = any { it.isUpperCase() }

    private fun String.hasDigits() = any { it.isDigit() }

    private fun String.hasLetter() = any { it.isLetter() }
}