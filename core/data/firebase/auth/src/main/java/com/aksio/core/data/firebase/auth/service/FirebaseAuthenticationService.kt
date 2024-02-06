package com.aksio.core.data.firebase.auth.service

import com.aksio.core.data.firebase.auth.models.NetworkAuthResponse
import com.aksio.core.models.auth.AuthenticationProvider
import com.aksio.core.models.auth.RegistrationRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FirebaseAuthenticationService @Inject constructor(
    private val authService: FirebaseAuth
) : AuthenticationService {

    override suspend fun register(request: RegistrationRequest): NetworkAuthResponse {
        return when (request) {
            is RegistrationRequest.Google -> TODO()
            is RegistrationRequest.Password -> {
                authService.createUserWithEmailAndPassword(request.email, request.password).await()
                NetworkAuthResponse(request.email, AuthenticationProvider.EMAIL)
            }
        }
    }

    override suspend fun authenticate() {
        TODO("Not yet implemented")
    }

    override suspend fun isAuthenticated() {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }

    override suspend fun sendVerificationEmail() {
        authService.currentUser
            ?.run { sendEmailVerification().await() }
            ?: throw Exception("User not authenticated")
    }

    override suspend fun isEmailVerified(): Boolean =
        authService.currentUser
            ?.run { isEmailVerified }
            ?: throw Exception("User not authenticated")

}