package com.aksio.core.data.db.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aksio.core.models.auth.AuthenticationProvider
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val isVerificationEmailSent: Boolean,
    val authProvider: AuthenticationProvider,
    val createdAt: OffsetDateTime,
    val lastLogin: OffsetDateTime
)
