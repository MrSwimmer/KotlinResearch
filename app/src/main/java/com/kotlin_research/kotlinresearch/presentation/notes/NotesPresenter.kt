package com.kotlin_research.kotlinresearch.presentation.settings

import android.annotation.SuppressLint
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.data.room.NoteDao
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import android.arch.paging.PagedList
import android.os.Handler
import android.os.Looper
import com.kotlin_research.kotlinresearch.data.paging.NoteDiffUtilCallback
import com.kotlin_research.kotlinresearch.data.paging.NotePositionalDataSource
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.di.DaggerAppComponent
import com.kotlin_research.kotlinresearch.presentation.notes.recycler.NotePagingAdapter
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class NotesPresenter : MvpBasePresenter<NotesContract.View>(), NotesContract.Presenter {

    @Inject
    lateinit var db: NoteDao

    init {
        App.getComponent().inject(this)
    }

    override fun setPagingRecyclerData() {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(2)
                .build()
        val pagedList = PagedList.Builder(NotePositionalDataSource(), config)
                .setNotifyExecutor(MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                /*.setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
                .setMainThreadExecutor(MainThreadExecutor())*/
                .build()

        view.setAdapter(pagedList)
        //var adapter = NotePagingAdapter(NoteDiffUtilCallback())
    }

    class MainThreadExecutor : Executor {

        override fun execute(command: Runnable?) {
            Handler(Looper.getMainLooper()).post(command)
        }
    }
}

