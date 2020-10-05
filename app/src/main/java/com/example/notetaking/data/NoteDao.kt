package com.example.notetaking.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notetaking.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNotes() : LiveData<List<Note>>


}