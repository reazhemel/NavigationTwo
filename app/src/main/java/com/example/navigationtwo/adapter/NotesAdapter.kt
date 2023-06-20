package com.example.navigationtwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationtwo.R
import com.example.navigationtwo.cache.NoteEntity

class NotesAdapter(
    val onItemCLick: (Int)->Unit
): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    lateinit var notes: List<NoteEntity>
    inner class NotesViewHolder(private val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.itemView.findViewById<TextView>(R.id.note_number_tv).text = note.id.toString()
        holder.itemView.findViewById<TextView>(R.id.note_author_tv).text = note.author_name
        holder.itemView.findViewById<TextView>(R.id.note_title_tv).text = note.note_title

        holder.itemView.setOnClickListener {
            onItemCLick(note.id)
        }
    }

    fun setNoteList(noteList: List<NoteEntity>){
        this.notes = noteList
        notifyDataSetChanged()
    }
}