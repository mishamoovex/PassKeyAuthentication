package com.aksio.core.data.db.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aksio.core.data.db.db.models.UserEntity
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime

@Dao
interface UserDao {

    @Insert
    suspend fun insert(entity: UserEntity): Long

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getCurrentUser(): UserEntity?

    @Query("SELECT * FROM user LIMIT 1")
    fun observeCurrentUser(): Flow<UserEntity?>

    @Query("UPDATE user SET emailVerificationSentAt=:sentAt WHERE id=:userId")
    suspend fun updateSentEmail(
        userId: Long,
        sentAt: OffsetDateTime
    )

}