package com.example.navigationtwo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtwo.cache.NoteEntity
import com.example.navigationtwo.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository
): ViewModel(){

    private val _noteList = MutableStateFlow<List<NoteEntity>>(emptyList())
    val noteList = _noteList.asStateFlow()

    private val _noteEntityState = MutableStateFlow(
        NoteEntity(0, "", "", "")
    )

    val noteEntity = _noteEntityState.asStateFlow()

    init {
        getAllNoteList()
    }

    fun insertNoteEntity(noteEntity: NoteEntity){
        viewModelScope.launch {
            noteRepository.insertNoteEntity(noteEntity = noteEntity)
        }
    }

    private fun getAllNoteList() {
        viewModelScope.launch {
            noteRepository.getAllNoteList().collect{ noteList ->
                _noteList.value = noteList
            }
        }
    }

    fun getSingleNoteDetails(noteId: Int){
        viewModelScope.launch {
            noteRepository.getSingleNoteDetails(noteId).collect{

            }
        }
    }
}