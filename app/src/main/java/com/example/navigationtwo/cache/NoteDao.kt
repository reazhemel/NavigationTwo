package com.example.navigationtwo.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table")
    fun getAllNoteList(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE id =:noteId")
    fun getSingleNoteDetails(noteId: Int): Flow<NoteEntity>
}