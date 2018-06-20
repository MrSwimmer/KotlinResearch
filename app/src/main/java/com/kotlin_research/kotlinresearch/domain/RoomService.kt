package com.kotlin_research.kotlinresearch.domain

import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.data.room.NoteDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RoomService(var db: NoteDao) {

    fun getInterval(today: Long, interval: Long, callback: StatCallback) {
        db.getInterval(today-interval)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<List<Note>>() {
                    override fun onSuccess(notes: List<Note>) {
                        callback.onSuccess(notes)
                    }

                    override fun onError(e: Throwable) {
                        callback.onError(e)
                    }
                })
    }

    interface StatCallback {
        fun onSuccess(notes: List<Note>)
        fun onError(e: Throwable)
    }
}