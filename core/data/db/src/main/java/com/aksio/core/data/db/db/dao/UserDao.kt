package com.aksio.core.data.db.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aksio.core.data.db.db.models.UserEntity
import java.time.OffsetDateTime

@Dao
interface UserDao {

    @Insert
    suspend fun insert(entity: UserEntity): Long

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity

    @Query("UPDATE user SET emailVerificationSentAt=:sentAt WHERE id=:userId")
    suspend fun updateSentEmail(
        userId: Long,
        sentAt: OffsetDateTime
    )

}