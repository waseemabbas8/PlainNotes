package com.tekprosoft.plainnotes.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tekprosoft.plainnotes.R
import com.tekprosoft.plainnotes.database.NoteEntity

class NotesAdapter(private val notes: ArrayList<NoteEntity>) : RecyclerView.Adapter<NotesAdapter.NotesVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesVH {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note_list, parent, false)
        return NotesVH(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesVH, position: Int) {
        val note = notes[position]
        holder.mNoteText.text = note.text
        holder.mFab.setOnClickListener {
            val intent = Intent()
        }
    }


    class NotesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mNoteText = itemView.findViewById<TextView>(R.id.note_text)!!
        val mFab: FloatingActionButton = itemView.findViewById(R.id.fab)
    }
}