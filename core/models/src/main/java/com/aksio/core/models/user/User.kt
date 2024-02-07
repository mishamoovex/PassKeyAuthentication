package com.aksio.core.models.user

import com.aksio.core.models.auth.AuthenticationProvider
import java.time.OffsetDateTime

data class User(
    val id: Long,
    val authProvider: AuthenticationProvider,
    val email: String,
    val emailVerificationSentAt: OffsetDateTime?,
    val createdAt: OffsetDateTime,
    val lastLogin: OffsetDateTime
)
