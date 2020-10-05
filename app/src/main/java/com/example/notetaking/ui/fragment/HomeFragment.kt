package com.example.notetaking.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaking.R
import com.example.notetaking.adapter.HomeFragmentAdapter
import com.example.notetaking.model.Note
import com.example.notetaking.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var noteViewModel : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val adapter = HomeFragmentAdapter()
        val recyclerView = view.home_recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        val itemTouchHelperCallBack = object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                val toPos = viewHolder.adapterPosition
                removeItem(adapter.noteList[toPos])
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        view.main_fab.setOnClickListener{
            findNavController().navigate(R.id.action_home_to_add)
        }

        return view

    }

    private fun removeItem(note: Note){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            noteViewModel.deleteNote(note)
            Toast.makeText(
                    requireContext(),
                    "Successfully removed: ",
                    Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${note.title}?")
        builder.setMessage("Are you sure you want to delete ${note.title}?")
        builder.create().show()
    }

}