package com.aksio.features.authentication.domain.password

interface ValidatePasswordUseCase {

    suspend operator fun invoke(password: String): List<PasswordValidationError>
}