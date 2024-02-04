package com.aksio.core.repository.authentication.repository

import com.aksio.core.data.db.db.dao.UserDao
import com.aksio.core.data.firebase.auth.service.AuthenticationService
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userDao: UserDao
) : AuthenticationRepository {
}