package com.aksio.core.data.db.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.aksio.core.data.db.db.models.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(entity: UserEntity): Long
}