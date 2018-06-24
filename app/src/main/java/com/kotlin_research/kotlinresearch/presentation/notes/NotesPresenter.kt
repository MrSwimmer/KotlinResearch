package com.kotlin_research.kotlinresearch.presentation.settings

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import android.arch.paging.PagedList
import android.os.Handler
import android.os.Looper
import com.kotlin_research.kotlinresearch.data.paging.NotePositionalDataSource
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class NotesPresenter : MvpBasePresenter<NotesContract.View>(), NotesContract.Presenter {


    init {
        App.getComponent().inject(this)
    }

    override fun setRecyclerData(currentPeriod: Int, moment: Int) {
        val today = Date().time
        val beginPeriod = today - getLongInterval(currentPeriod)
        val withPeriod = currentPeriod != 0
        val withMoment = moment != 2
        var afterSleep = true
        when (moment) {
            0 -> afterSleep = true
            1 -> afterSleep = false
        }
        lateinit var positionalDataSource: NotePositionalDataSource
        positionalDataSource = if (withPeriod) {
            if (withMoment)
                NotePositionalDataSource(beginPeriod, afterSleep)
            else
                NotePositionalDataSource(beginPeriod)
        } else {
            if (withMoment)
                NotePositionalDataSource(afterSleep)
            else
                NotePositionalDataSource()
        }

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(8)
                .build()
        val pagedList = PagedList.Builder(positionalDataSource, config)
                .setNotifyExecutor(MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
        view.setAdapter(pagedList)
    }

    class MainThreadExecutor : Executor {
        override fun execute(command: Runnable?) {
            Handler(Looper.getMainLooper()).post(command)
        }
    }

    private fun getLongInterval(period: Int): Long {
        when (period) {
            0 -> return -1
            1 -> return 31536000000
            2 -> return 2592000000
            3 -> return 604800000
        }
        return 604800000
    }
}

