package com.example.adiblarhayoti.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.adiblarhayoti.models.Writer

@Dao
interface WriterDao {

    @Insert
    fun insertWriter(writer: Writer)

    @Delete
    fun deleteWriter(writer: Writer)

    @Query("select * from writer")
    fun getWriters():List<Writer>

}