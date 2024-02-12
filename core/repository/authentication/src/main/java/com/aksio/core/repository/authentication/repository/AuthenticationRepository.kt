package com.aksio.core.repository.authentication.repository

import com.aksio.core.models.auth.RegistrationRequest
import com.aksio.core.models.user.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    suspend fun register(request: RegistrationRequest)

    suspend fun getCurrentUser(): User?

    fun observeCurrentUser(): Flow<User?>

    suspend fun isAuthenticated(): Boolean

    suspend fun isEmailVerificationSent(): Boolean

    suspend fun sendVerificationEmail()
}