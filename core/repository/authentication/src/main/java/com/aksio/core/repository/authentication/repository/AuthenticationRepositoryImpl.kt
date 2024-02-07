package com.aksio.core.repository.authentication.repository

import com.aksio.core.data.db.db.dao.UserDao
import com.aksio.core.data.db.db.models.UserEntity
import com.aksio.core.data.firebase.auth.service.AuthenticationService
import com.aksio.core.models.auth.RegistrationRequest
import java.time.Clock
import java.time.OffsetDateTime
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userDao: UserDao,
    private val clock: Clock
) : AuthenticationRepository {

    override suspend fun registerUser(request: RegistrationRequest) {
        val authResponse = authenticationService.register(request)
        val creationTime = OffsetDateTime.now(clock)
        val user = UserEntity(
            email = authResponse.email,
            authProvider = authResponse.provider,
            createdAt = creationTime,
            lastLogin = creationTime
        )
        userDao.insert(user)
    }

    override suspend fun isAuthenticated(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun isEmailVerificationSent(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun sendVerificationEmail() {
        authenticationService.sendVerificationEmail()
        userDao.updateSentEmail(
            userId = userDao.getUser().id,
            sentAt = OffsetDateTime.now(clock)
        )
    }
}