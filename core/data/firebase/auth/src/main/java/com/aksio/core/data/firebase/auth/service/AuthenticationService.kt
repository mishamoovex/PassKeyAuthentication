package com.aksio.core.data.firebase.auth.service

import com.aksio.core.data.firebase.auth.models.NetworkAuthResponse
import com.aksio.core.models.auth.RegistrationRequest

interface AuthenticationService {

    suspend fun register(request: RegistrationRequest): NetworkAuthResponse

    suspend fun authenticate()

    suspend fun isAuthenticated()

    suspend fun signOut()

    suspend fun sendVerificationEmail()

    suspend fun isEmailVerified()
}