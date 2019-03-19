package com.tekprosoft.plainnotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null
        private const val DATABASE_NAME = "PlainNote.db"

        fun getDatabase(context: Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context,
                    AppDatabase::class.java, DATABASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}