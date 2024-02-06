@file:Suppress("unused")

package com.aksio.core.data.db.db

import androidx.room.TypeConverter
import java.time.OffsetDateTime

internal class DbTypeConverters {

    @TypeConverter
    fun fromLocalDateTimer(value: OffsetDateTime?): String? = value?.toString()

    @TypeConverter
    fun toLocalDateTime(value: String?): OffsetDateTime? = value?.let {
        OffsetDateTime.parse(value)
    }

}