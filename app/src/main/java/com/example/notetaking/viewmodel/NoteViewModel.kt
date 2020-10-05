package com.example.notetaking.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notetaking.data.NoteDatabase
import com.example.notetaking.model.Note
import com.example.notetaking.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (application: Application): AndroidViewModel(application) {

    val getAllNotes: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val userDao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(userDao)
        getAllNotes = repository.getAllNotes
    }

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }




}