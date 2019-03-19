package com.tekprosoft.plainnotes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUpdateNote(noteEntity: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(notes: List<NoteEntity>)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteById(id : Int) : NoteEntity

    @Query("SELECT * FROM note ORDER BY date DESC")
    fun getAll() : LiveData<List<NoteEntity>>

    @Query("DELETE FROM note")
    fun deleteAllNotes()

    @Query("SELECT COUNT(*) FROM note")
    fun getCount() : Int
}