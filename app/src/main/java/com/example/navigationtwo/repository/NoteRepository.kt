package com.example.navigationtwo.repository

import com.example.navigationtwo.cache.NoteDatabase
import com.example.navigationtwo.cache.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepository(
    private val database: NoteDatabase
) {
    suspend fun insertNoteEntity(noteEntity: NoteEntity){
        database.noteDao().insertNoteEntity(noteEntity = noteEntity)
    }

    fun getAllNoteList(): Flow<List<NoteEntity>> {
       return database.noteDao().getAllNoteList()
    }

    fun getSingleNoteDetails(noteId: Int): Flow<NoteEntity> {
        return database.noteDao().getSingleNoteDetails(noteId)
    }
}