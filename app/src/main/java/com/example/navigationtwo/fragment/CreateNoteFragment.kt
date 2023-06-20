package com.example.navigationtwo.fragment

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.navigationtwo.R
import com.example.navigationtwo.cache.NoteDatabase
import com.example.navigationtwo.cache.NoteEntity
import com.example.navigationtwo.databinding.FragmentCreateNoteBinding
import com.example.navigationtwo.repository.NoteRepository
import com.example.navigationtwo.viewmodel.NoteViewModel

class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    private lateinit var viewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater)

        val noteDatabase = Room.databaseBuilder(
            requireActivity(),
            NoteDatabase::class.java,
            "note_db"
        ).build()

        val noteRepository = NoteRepository(noteDatabase)
        viewModel = NoteViewModel(noteRepository)

        insertSingleNote()

        return binding.root
    }

    private fun insertSingleNote(){
        binding.apply {
            createNoteBtn.setOnClickListener {
                val authorName = authorTextViewCreate.text.trim().toString()
                val noteTitle = noteTitleTextViewCreate.text.trim().toString()
                val noteBody = noteBodyTextViewCreate.text.trim().toString()

                viewModel.insertNoteEntity(
                    NoteEntity(
                        author_name = authorName,
                        note_title = noteTitle,
                        note_body = noteBody
                    )
                )
                findNavController().navigate(
                    CreateNoteFragmentDirections.actionCreateNoteFragmentToNoteListFragment()
                )
            }
        }
    }
}