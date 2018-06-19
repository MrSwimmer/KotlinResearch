package com.kotlin_research.kotlinresearch.domain

import android.util.Log
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.data.room.NoteDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RoomService(var db: NoteDao) {
    fun getWeek(today: Long, callback: StatCallback) {
        db.getWeek(today-604800000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<List<Note>>() {
                    override fun onSuccess(t: List<Note>) {
                        callback.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("code", "error ${e.message}")
                    }
                })
    }

    interface StatCallback {
        fun onSuccess(notes: List<Note>)
    }
}