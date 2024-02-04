package com.aksio.core.data.db.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aksio.core.models.auth.AuthenticationProvider
import java.time.LocalDateTime

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val authProvider: AuthenticationProvider,
    val createdAt: LocalDateTime,
    val lastLogin: LocalDateTime
)
