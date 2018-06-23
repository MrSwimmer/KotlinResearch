package com.kotlin_research.kotlinresearch.domain.interactor

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.data.room.NoteDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import rx.Observable
import rx.Subscriber

class RoomService(var db: NoteDao) {

    fun getInterval(today: Long, interval: Long, afterSleep: Int, callback: NotesCallback) {
        var afterSleepBool = false
        when (afterSleep) {
            0 -> afterSleepBool = true
            1 -> afterSleepBool = false
            2 -> {
                db.getIntervalAll(today - interval)
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
                return
            }
        }
        db.getIntervalWithFilter(today - interval, afterSleepBool)
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

    fun addNote(note: Note, callback: AddNoteCallback) {
        Observable.create(Observable.OnSubscribe<String> { subscriber ->
            db.insert(note)
            subscriber.onCompleted()
        })
                .subscribeOn(rx.schedulers.Schedulers.io())
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<String>() {
                    override fun onNext(t: String?) {
                    }

                    override fun onCompleted() {
                        callback.onSuccess()
                    }

                    override fun onError(e: Throwable) {
                        callback.onError(e)
                    }

                })
    }

    fun getRange(params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        db.getPage(params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<List<Note>>() {
                    override fun onSuccess(t: List<Note>) {
                        Log.i("code", "load range ${t.size}")
                        callback.onResult(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("code", "error ${e.message}")
                    }
                })
    }

    fun getFirstPage(params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        db.getFirstPage(params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<List<Note>>() {
                    override fun onSuccess(t: List<Note>) {
                        Log.i("code", "first load range ${t.size}")
                        callback.onResult(t, 0)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("code", "first error ${e.message}")
                    }
                })
    }

    fun getFilterRange(today: Long, interval: Long, afterSleep: Int, params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        var afterSleepBool = false
        when (afterSleep) {
            0 -> afterSleepBool = true
            1 -> afterSleepBool = false
            2 -> {
                db.getFilterPage(params.startPosition, params.loadSize)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : DisposableSingleObserver<List<Note>>() {
                            override fun onSuccess(t: List<Note>) {
                                Log.i("code", "load range ${t.size}")
                                callback.onResult(t)
                            }

                            override fun onError(e: Throwable) {
                                Log.i("code", "error ${e.message}")
                            }
                        })
                return
            }
        }
        db.getFilterPageWithMoment(params.startPosition, params.loadSize, afterSleepBool)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<List<Note>>() {
                    override fun onSuccess(t: List<Note>) {
                        Log.i("code", "load range ${t.size}")
                        callback.onResult(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("code", "error ${e.message}")
                    }
                })
    }

    fun getFilterFirstPage(time: Long, longInterval: Long, afterSleep: Int, params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        var afterSleepBool = false
        when (afterSleep) {
            0 -> afterSleepBool = true
            1 -> afterSleepBool = false
            2 -> {
                db.getFilterFirstPage(params.pageSize, time, longInterval)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : DisposableSingleObserver<List<Note>>() {
                            override fun onSuccess(t: List<Note>) {
                                Log.i("code", "first load range ${t.size}")
                                callback.onResult(t, 0)
                            }

                            override fun onError(e: Throwable) {
                                Log.i("code", "first error ${e.message}")
                            }
                        })
                return
            }
            db.getFilterFirstPageWithMoment(params.pageSize, time, longInterval, afterSleepBool)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : DisposableSingleObserver<List<Note>>() {
                        override fun onSuccess(t: List<Note>) {
                            Log.i("code", "first load range ${t.size}")
                            callback.onResult(t, 0)
                        }

                        override fun onError(e: Throwable) {
                            Log.i("code", "first error ${e.message}")
                        }
                    })
        }
    }


    interface NotesCallback {
        fun onSuccess(notes: List<Note>)
        fun onError(e: Throwable)
    }

    interface AddNoteCallback {
        fun onSuccess()
        fun onError(e: Throwable)
    }
}