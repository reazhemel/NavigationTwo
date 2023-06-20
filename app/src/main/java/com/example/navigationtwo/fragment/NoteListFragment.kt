package com.example.navigationtwo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.navigationtwo.R
import com.example.navigationtwo.adapter.NotesAdapter
import com.example.navigationtwo.cache.NoteDatabase
import com.example.navigationtwo.databinding.FragmentNoteListBinding
import com.example.navigationtwo.repository.NoteRepository
import com.example.navigationtwo.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class NoteListFragment : Fragment() {

    lateinit var binding: FragmentNoteListBinding
    lateinit var viewModel: NoteViewModel
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(layoutInflater)
        binding.createNoteFab.setOnClickListener {
            findNavController().navigate(
                NoteListFragmentDirections.actionNoteListFragmentToCreateNoteFragment()
            )
        }

        val noteDatabase = Room.databaseBuilder(
            requireActivity(),
            NoteDatabase::class.java,
            "note_db"
        ).build()
        adapter = NotesAdapter(
            onItemCLick = {id->
                findNavController().navigate(
                    NoteListFragmentDirections.actionNoteListFragmentToNoteDetailsFragment(id)
                )
            }
        )
        val noteRepository = NoteRepository(noteDatabase)
        viewModel = NoteViewModel(noteRepository)

        setUpAdapter()
        return binding.root
    }

    private fun setUpAdapter(){
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(
            requireActivity()
        )
        binding.notesRecyclerView.setHasFixedSize(true)
        binding.notesRecyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.noteList.collect{
                    adapter.setNoteList(it)
                }
            }
        }
    }

}