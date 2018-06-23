package com.kotlin_research.kotlinresearch.data.paging

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.domain.interactor.RoomService
import javax.inject.Inject

class NotePositionalDataSource() : PositionalDataSource<Note>() {

    var filter = false
    var longInterval: Long = 0
    var moment: Int = 0
    var time: Long = 0

    constructor(time: Long, longInterval: Long, moment: Int) : this() {
        filter = true
        this.time = time
        this.longInterval = longInterval
        this.moment = moment
    }

    init {
        App.getComponent().inject(this)
    }

    @Inject
    lateinit var db: RoomService

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Note>) {
        Log.i("code", "range filter $filter")
        if (filter) {
            db.getFilterRange(time, longInterval, moment, params, callback)
        } else {
            db.getRange(params, callback)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Note>) {
        Log.i("code", "range filter $filter")
        if (!filter) {
            db.getFirstPage(params, callback)
        } else {
            db.getFilterFirstPage(time, longInterval, moment, params, callback)
        }
    }
}