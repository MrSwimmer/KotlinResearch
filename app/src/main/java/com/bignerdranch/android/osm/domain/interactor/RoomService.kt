package com.bignerdranch.android.osm.domain.interactor

import android.arch.paging.PositionalDataSource
import com.bignerdranch.android.osm.data.room.Note
import com.bignerdranch.android.osm.data.room.NoteDao
import com.bignerdranch.android.osm.domain.CallBackFabric
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rx.Observable

class RoomService(var db: NoteDao) {
    fun setCallback(a: Single<List<Note>>, callback: NotesCallback) {
        a.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteCallback(callback))
    }

    fun getInterval(callback: NotesCallback) {
        db.getInterval()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteCallback(callback))
    }

    fun getIntervalFilterMoment(afterSleep: Boolean, callback: NotesCallback) {
        db.getIntervalFilterMoment(afterSleep)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteCallback(callback))
    }

    fun getIntervalFilterPeriod(beginPeriod: Long, callback: NotesCallback) {
        db.getIntervalFilterPeriod(beginPeriod)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteCallback(callback))
    }

    fun getIntervalFilterAll(beginPeriod: Long, afterSleep: Boolean, callback: NotesCallback) {
        db.getIntervalFilterAll(beginPeriod, afterSleep)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteCallback(callback))
    }

    fun addNote(note: Note, callback: EditNoteCallback) {
        Observable.create(Observable.OnSubscribe<String> { subscriber ->
            db.insert(note)
            subscriber.onCompleted()
        })
                .subscribeOn(rx.schedulers.Schedulers.io())
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getCallback(callback))
    }

    fun deleteAll(callback: EditNoteCallback) {
        Observable.create(Observable.OnSubscribe<String> { subscriber ->
            db.deleteAll()
            subscriber.onCompleted()
        })
                .subscribeOn(rx.schedulers.Schedulers.io())
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getCallback(callback))
    }

    fun getRange(params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        db.getPage(params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNotePageCallback(callback))
    }

    fun getRangeFilterMoment(afterSleep: Boolean, params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        db.getPageFilterMoment(afterSleep, params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNotePageCallback(callback))
    }

    fun getRangeFilterPeriod(beginPeriod: Long, params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        db.getPageFilterPeriod(beginPeriod, params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNotePageCallback(callback))
    }

    fun getRangeFilterAll(beginPeriod: Long, afterSleep: Boolean, params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Note>) {
        db.getPageFilterAll(beginPeriod, afterSleep, params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNotePageCallback(callback))
    }

    fun getFirstPage(params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        db.getFirstPage(params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteFirstPageCallback(callback))
    }

    fun getFirstPageFilterMoment(afterSleep: Boolean, params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        db.getFirstPageFilterMoment(afterSleep, params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteFirstPageCallback(callback))
    }

    fun getFirstPageFilterPeriod(beginPeriod: Long, params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        db.getFirstPageFilterPeriod(beginPeriod, params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteFirstPageCallback(callback))
    }

    fun getFirstPageFilterAll(beginPeriod: Long, afterSleep: Boolean, params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Note>) {
        db.getFirstPageFilterAll(beginPeriod, afterSleep, params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallBackFabric.getNoteFirstPageCallback(callback))
    }

    interface NotesCallback {
        fun onSuccess(notes: List<Note>)
        fun onError(e: Throwable)
    }

    interface EditNoteCallback {
        fun onSuccess()
        fun onError(e: Throwable)
    }
}