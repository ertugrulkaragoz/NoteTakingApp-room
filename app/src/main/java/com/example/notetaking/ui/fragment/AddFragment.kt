package com.example.notetaking.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notetaking.R
import com.example.notetaking.model.Note
import com.example.notetaking.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.add_fab.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){

        val noteTitle = note_title.text.toString().trim()
        val noteBody = note_body.text.toString().trim()

        if (inputCheck(noteTitle,noteBody)) {

            val note = Note(noteTitle,noteBody)
            noteViewModel.addNote(note)
            findNavController().navigate(R.id.action_add_to_home)
        }else{
            Toast.makeText(activity,"Please fill out all fields",Toast.LENGTH_LONG).show()
        }


    }

    private fun inputCheck(noteTitle: String, noteBody: String) : Boolean {
        return !(TextUtils.isEmpty(noteTitle) && TextUtils.isEmpty(noteBody))
    }


}