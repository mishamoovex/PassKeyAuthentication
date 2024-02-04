@file:Suppress("unused")

package com.aksio.core.data.db.db

import androidx.room.TypeConverter
import java.time.LocalDateTime

internal class DbTypeConverters {

    @TypeConverter
    fun fromLocalDateTimer(value: LocalDateTime?): String? = value?.toString()

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? = value?.let {
        LocalDateTime.parse(value)
    }

}