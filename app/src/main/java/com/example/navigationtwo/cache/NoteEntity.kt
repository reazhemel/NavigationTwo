package com.example.navigationtwo.cache

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author_name: String,
    val note_title: String,
    val note_body: String
)
