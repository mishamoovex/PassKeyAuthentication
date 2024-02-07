package com.aksio.core.repository.authentication.repository

import com.aksio.core.models.auth.RegistrationRequest

interface AuthenticationRepository {

    suspend fun registerUser(request: RegistrationRequest)

    suspend fun isAuthenticated(): Boolean

    suspend fun isEmailVerificationSent(): Boolean

    suspend fun sendVerificationEmail()
}