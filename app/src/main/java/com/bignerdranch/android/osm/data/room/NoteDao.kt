package com.bignerdranch.android.osm.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Update
import io.reactivex.Single

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getById(id: Long): Note

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAll()

    @Update
    fun update(note: Note)

    //new room
    //statistic
    @Query("SELECT * FROM notes")
    fun getInterval(): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE afterSleep = :afterSleep")
    fun getIntervalFilterMoment(afterSleep: Boolean): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod")
    fun getIntervalFilterPeriod(beginPeriod: Long): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod AND afterSleep = :afterSleep")
    fun getIntervalFilterAll(beginPeriod: Long, afterSleep: Boolean): Single<List<Note>>

    //page
    //range
    @Query("SELECT * FROM notes LIMIT :startPosition, :loadSize")
    fun getPage(startPosition: Int, loadSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE afterSleep = :afterSleep LIMIT :startPosition, :loadSize")
    fun getPageFilterMoment(afterSleep: Boolean, startPosition: Int, loadSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod LIMIT :startPosition, :loadSize")
    fun getPageFilterPeriod(beginPeriod: Long, startPosition: Int, loadSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod AND afterSleep = :afterSleep LIMIT :startPosition, :loadSize")
    fun getPageFilterAll(beginPeriod: Long, afterSleep: Boolean, startPosition: Int, loadSize: Int): Single<List<Note>>

    //first page
    @Query("SELECT * FROM notes LIMIT :pageSize")
    fun getFirstPage(pageSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE afterSleep = :afterSleep LIMIT :pageSize")
    fun getFirstPageFilterMoment(afterSleep: Boolean, pageSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod LIMIT :pageSize")
    fun getFirstPageFilterPeriod(beginPeriod: Long, pageSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod AND afterSleep = :afterSleep LIMIT :pageSize")
    fun getFirstPageFilterAll(beginPeriod: Long, afterSleep: Boolean, pageSize: Int): Single<List<Note>>
}