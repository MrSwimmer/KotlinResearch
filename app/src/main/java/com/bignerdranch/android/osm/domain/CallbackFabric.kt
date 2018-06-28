package com.bignerdranch.android.osm.domain

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.bignerdranch.android.osm.data.room.Note
import com.bignerdranch.android.osm.domain.interactor.RoomService
import io.reactivex.observers.DisposableSingleObserver
import rx.Subscriber

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

        fun getCallback(callback: RoomService.EditNoteCallback): Subscriber<String> {
            return object : Subscriber<String>() {
                override fun onNext(t: String?) {
                }

                override fun onCompleted() {
                    callback.onSuccess()
                }

                override fun onError(e: Throwable) {
                    callback.onError(e)
                }

            }
        }
    }
}