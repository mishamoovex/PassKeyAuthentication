package com.aksio.features.authentication.domain.email

import android.util.Patterns
import javax.inject.Inject

class ValidateEmailUseCaseImpl @Inject constructor() : ValidateEmailUseCase {

    override suspend fun invoke(email: String): List<EmailValidationError> {
        if (email.isBlank() && email.isEmpty()) return listOf(EmailValidationError.EMPTY)
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return if (isValid) emptyList() else listOf(EmailValidationError.INVALID)
    }
}