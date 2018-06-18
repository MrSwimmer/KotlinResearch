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
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class NotesPresenter : MvpBasePresenter<NotesContract.View>(), NotesContract.Presenter {

    @Inject
    lateinit var db: NoteDao

    init {
        App.getComponent().inject(this)
    }

    @SuppressLint("CheckResult")
    override fun setRecyclerData() {
        db.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { notes ->
                    run {
                        Log.i("code", "accept " + notes.size)
                        if (view != null)
                            view.initAdapter(notes)
                    }
                }
    }

    override fun setPagingRecyclerData() {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build()



        val pagedList = PagedList.Builder<Any, Any>(dataSource, config)
                .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
                .setMainThreadExecutor(MainThreadExecutor())
                .build()
    }

    class MainThreadExecutor : Executor {

        override fun execute(command: Runnable?) {
            Handler(Looper.getMainLooper()).post(command)
        }
    }
}

