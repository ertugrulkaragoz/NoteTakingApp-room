package com.example.notetaking.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notetaking.R
import com.example.notetaking.adapter.HomeFragmentAdapter
import com.example.notetaking.model.Note
import com.example.notetaking.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private lateinit var noteViewModel : NoteViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.update_note_title.setText(args.currentNote.title)
        view.update_note_body.setText(args.currentNote.note)

        view.update_button.setOnClickListener{
            updateData()

        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.menu_delete,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.note_menu_delete) {
            deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            noteViewModel.deleteNote(args.currentNote)
            Toast.makeText(
                    requireContext(),
                    "Successfully removed: ",
                    Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_update_to_home)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentNote.title}?")
        builder.setMessage("Are you sure you want to delete ${args.currentNote.title}?")
        builder.create().show()
    }

    private fun updateData(){
        val noteTitle = update_note_title.text.toString()
        val noteBody = update_note_body.text.toString()
        val note = Note(noteTitle,noteBody)
        if (inputCheck(noteTitle,noteBody)) {
            note.Id = args.currentNote.Id
            noteViewModel.updateNote(note)
            findNavController().navigate(R.id.action_update_to_home)
        }
    }

    private fun inputCheck(noteTitle: String, noteBody: String) : Boolean {
        return !(TextUtils.isEmpty(noteTitle) && TextUtils.isEmpty(noteBody))
    }


}