package com.kotlin_research.kotlinresearch.data.paging

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.data.room.NoteDao
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import rx.Subscriber
import javax.inject.Inject

class NotePositionalDataSource : PositionalDataSource<Note>() {

    init {
        App.getComponent().inject(this)
    }

    @Inject
    lateinit var db: NoteDao

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Note>) {
        db.getPage(params.startPosition, params.loadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Note>> {
                    override fun onSuccess(t: List<Note>) {
                        Log.i("code", "load range ${t.size}")
                        callback.onResult(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.i("code", "subscribe")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("code", "error ${e.message}")
                    }
                })
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Note>) {
        db.getFirstPage(params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Note>> {
                    override fun onSuccess(t: List<Note>) {
                        Log.i("code", "first load range ${t.size}")
                        callback.onResult(t, 0)
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.i("code", "first subscribe")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("code", "first error ${e.message}")
                    }
                })
    }
}