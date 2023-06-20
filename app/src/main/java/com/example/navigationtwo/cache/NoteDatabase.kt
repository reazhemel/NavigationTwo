package com.example.navigationtwo.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase(): RoomDatabase(){
    abstract fun noteDao(): NoteDao
}