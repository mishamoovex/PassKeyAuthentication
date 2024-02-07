package com.aksio.core.data.db.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aksio.core.models.auth.AuthenticationProvider
import java.time.OffsetDateTime

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val authProvider: AuthenticationProvider,
    val email: String,
    val emailVerificationSentAt: OffsetDateTime? = null,
    val createdAt: OffsetDateTime,
    val lastLogin: OffsetDateTime
)
