package com.aksio.core.repository.authentication.mapper

import com.aksio.core.data.db.db.models.UserEntity
import com.aksio.core.models.user.User

internal fun UserEntity.asUser() = User(
    id = id,
    authProvider = authProvider,
    email = email,
    emailVerificationSentAt = emailVerificationSentAt,
    createdAt = createdAt,
    lastLogin = lastLogin
)