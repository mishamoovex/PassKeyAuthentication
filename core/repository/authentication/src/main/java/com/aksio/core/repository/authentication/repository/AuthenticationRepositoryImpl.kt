package com.aksio.core.repository.authentication.repository

import com.aksio.core.data.db.db.dao.UserDao
import com.aksio.core.data.db.db.models.UserEntity
import com.aksio.core.data.firebase.auth.service.AuthenticationService
import com.aksio.core.models.auth.RegistrationRequest
import com.aksio.core.models.user.User
import com.aksio.core.repository.authentication.mapper.asUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Clock
import java.time.OffsetDateTime
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userDao: UserDao,
    private val clock: Clock
) : AuthenticationRepository {

    override suspend fun register(request: RegistrationRequest) {
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

    override suspend fun getCurrentUser(): User? =
        userDao.getCurrentUser()?.asUser()

    override fun observeCurrentUser(): Flow<User?> =
        userDao.observeCurrentUser().map { entity -> entity?.asUser() }

    override suspend fun isAuthenticated(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun isEmailVerificationSent(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun sendVerificationEmail() {
        val userId = userDao.getCurrentUser()
            ?.id
            ?: throw IllegalStateException("User is not authenticated")

        authenticationService.sendVerificationEmail()

        userDao.updateSentEmail(
            userId = userId,
            sentAt = OffsetDateTime.now(clock)
        )
    }
}