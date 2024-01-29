package com.aksio.features.authentication.domain.email

interface ValidateEmailUseCase {

    suspend operator fun invoke(email: String): List<EmailValidationError>
}