package com.example.adiblarhayoti.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.adiblarhayoti.models.Writer

@Database(entities = [Writer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context):AppDatabase{
            if (INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context,AppDatabase::class.java,"writer-db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as AppDatabase
        }

    }
    abstract fun writerDao():WriterDao
}