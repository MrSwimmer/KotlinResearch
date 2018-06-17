package com.kotlin_research.kotlinresearch.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import java.util.*
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Update
import io.reactivex.Flowable

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM notes")
    fun getAll(): Flowable<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getById(id: Long): Note

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)
}