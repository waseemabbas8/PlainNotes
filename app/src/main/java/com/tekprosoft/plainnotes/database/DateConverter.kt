package com.tekprosoft.plainnotes.database

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    companion object {

        @JvmStatic
        @TypeConverter
        fun toDate(timestamp : Long) : Date{
            return Date(timestamp)
        }

        @JvmStatic
        @TypeConverter
        fun toTimestamp(date: Date) : Long{
            return date.time
        }
    }
}