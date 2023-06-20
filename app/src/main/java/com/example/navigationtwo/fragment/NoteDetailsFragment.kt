package com.example.navigationtwo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.example.navigationtwo.R
import com.example.navigationtwo.cache.NoteDatabase
import com.example.navigationtwo.databinding.FragmentNoteDetailsBinding
import com.example.navigationtwo.repository.NoteRepository
import com.example.navigationtwo.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class NoteDetailsFragment : Fragment() {
    lateinit var binding: FragmentNoteDetailsBinding
    private lateinit var  viewModel: NoteViewModel

    private val args by navArgs<NoteDetailsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailsBinding.inflate(layoutInflater)

        val noteDatabase = Room.databaseBuilder(
            requireActivity(),
            NoteDatabase::class.java,
            "Note_db"
        ).build()

        val noteRepository = NoteRepository(noteDatabase)

        viewModel = NoteViewModel(noteRepository)

        setUpView()
        return binding.root
    }

    private fun setUpView(){
        viewModel.getSingleNoteDetails(args.id)
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED){
                    viewModel.noteEntity.collect{
                        Log.i("Mytag", it.toString())
                        binding.noteTitleTextView.text = it.note_title
                        binding.noteBodyTextView.text = it.note_body
                    }
                }

        }
    }

}