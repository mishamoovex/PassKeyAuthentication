package com.aksio.core.data.db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aksio.core.data.db.db.dao.UserDao
import com.aksio.core.data.db.db.models.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DbTypeConverters::class
)
internal abstract class ApplicationDb : RoomDatabase() {
    abstract fun createUserDao(): UserDao

}