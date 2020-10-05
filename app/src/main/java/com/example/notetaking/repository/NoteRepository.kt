package com.example.notetaking.repository

import androidx.lifecycle.LiveData
import com.example.notetaking.data.NoteDao
import com.example.notetaking.model.Note

class NoteRepository(private val noteDao: NoteDao){
    val getAllNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

}