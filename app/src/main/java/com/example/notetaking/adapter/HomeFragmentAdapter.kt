package com.example.notetaking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaking.R
import com.example.notetaking.model.Note
import com.example.notetaking.ui.fragment.HomeFragmentDirections
import kotlinx.android.synthetic.main.item_list_notes.view.*

class HomeFragmentAdapter : RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentHolder>() {

    var noteList = emptyList<Note>()

    class HomeFragmentHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_list_notes,parent,false)
        return HomeFragmentHolder(view)
    }

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: HomeFragmentHolder, position: Int) {
        val currentNote = noteList[position]
        holder.itemView.id_text_view.text = (position+1).toString()
        holder.itemView.note_title_text_view.text = currentNote.title
        holder.itemView.note_body_text_view.text = currentNote.note
        holder.itemView.list_note_layout.setOnClickListener{

            val action = HomeFragmentDirections.actionHomeToUpdate(currentNote)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }
}