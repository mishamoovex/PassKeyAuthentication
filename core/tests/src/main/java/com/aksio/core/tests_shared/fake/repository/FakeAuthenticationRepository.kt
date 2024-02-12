package com.aksio.core.tests_shared.fake.repository

import com.aksio.core.models.auth.AuthenticationProvider
import com.aksio.core.models.auth.RegistrationRequest
import com.aksio.core.models.user.User
import com.aksio.core.repository.authentication.repository.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.time.Clock
import java.time.OffsetDateTime
import java.util.concurrent.atomic.AtomicBoolean

class FakeAuthenticationRepository(
    private val clock: Clock
) : AuthenticationRepository {

    companion object {
        const val GOOGLE_AUTH_EMAIL = "goole@gmail.com"
    }

    private val isFailedRequest = AtomicBoolean(false)
    private val userProfile = MutableStateFlow<User?>(null)

    override suspend fun register(request: RegistrationRequest) = onSuccessfulRequest {
        val authProvider = when (request) {
            is RegistrationRequest.Google -> AuthenticationProvider.GOOGLE
            is RegistrationRequest.Password -> AuthenticationProvider.EMAIL
        }
        val email = when (request) {
            is RegistrationRequest.Google -> GOOGLE_AUTH_EMAIL
            is RegistrationRequest.Password -> request.email
        }
        val timestamp = OffsetDateTime.now(clock)
        val user = User(
            id = 0,
            authProvider = authProvider,
            email = email,
            emailVerificationSentAt = null,
            createdAt = timestamp,
            lastLogin = timestamp
        )
        userProfile.update { user }
    }

    override suspend fun isAuthenticated(): Boolean =
        userProfile.value != null

    override suspend fun isEmailVerificationSent(): Boolean = onSuccessfulRequest {
        val user = userProfile.value ?: throw IllegalStateException("User is not authorized")
        user.emailVerificationSentAt != null
    }

    override suspend fun sendVerificationEmail() = onSuccessfulRequest {
        val timestamp = OffsetDateTime.now(clock)
        userProfile.update {
            it?.copy(emailVerificationSentAt = timestamp)
        }
    }

    fun shouldThrowException(isFailedRequest: Boolean) {
        this.isFailedRequest.set(isFailedRequest)
    }

    private inline fun <T> onSuccessfulRequest(block: () -> T): T = if (isFailedRequest.get()) {
        throw Exception()
    } else {
        block()
    }
}