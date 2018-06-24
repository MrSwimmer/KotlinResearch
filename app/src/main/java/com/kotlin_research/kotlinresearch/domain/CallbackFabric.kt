package com.kotlin_research.kotlinresearch.domain

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.domain.interactor.RoomService
import io.reactivex.observers.DisposableSingleObserver

class CallBackFabric {
    companion object {
        fun getNoteCallback(callback: RoomService.NotesCallback): DisposableSingleObserver<List<Note>> {
            return object : DisposableSingleObserver<List<Note>>() {
                override fun onSuccess(notes: List<Note>) {
                    callback.onSuccess(notes)
                }

                override fun onError(e: Throwable) {
                    callback.onError(e)
                }
            }
        }

        fun getNotePageCallback(callback: PositionalDataSource.LoadRangeCallback<Note>): DisposableSingleObserver<List<Note>> {
            return object : DisposableSingleObserver<List<Note>>() {
                override fun onSuccess(notes: List<Note>) {
                    callback.onResult(notes)
                }

                override fun onError(e: Throwable) {

                }
            }
        }

        fun getNoteFirstPageCallback(callback: PositionalDataSource.LoadInitialCallback<Note>): DisposableSingleObserver<List<Note>> {
            return object : DisposableSingleObserver<List<Note>>() {
                override fun onSuccess(t: List<Note>) {
                    Log.i("code", "first load range ${t.size}")
                    callback.onResult(t, 0)
                }

                override fun onError(e: Throwable) {
                    Log.i("code", "first error ${e.message}")
                }
            }
        }
    }
}