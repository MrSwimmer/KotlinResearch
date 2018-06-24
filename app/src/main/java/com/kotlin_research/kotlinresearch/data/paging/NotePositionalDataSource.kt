package com.kotlin_research.kotlinresearch.data.paging

import android.arch.paging.PositionalDataSource
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.domain.interactor.RoomService
import javax.inject.Inject

class NotePositionalDataSource() : PositionalDataSource<Note>() {
    var type = 0
    var afterSleep = false
    var beginPeriod: Long = 0

    constructor(afterSleep: Boolean) : this() {
        type = 1
        this.afterSleep = afterSleep
    }

    constructor(beginPeriod: Long) : this() {
        type = 2
        this.beginPeriod = beginPeriod
    }

    constructor(beginPeriod: Long, afterSleep: Boolean) : this() {
        type = 3
        this.afterSleep = afterSleep
        this.beginPeriod = beginPeriod
    }

    init {
        App.getComponent().inject(this)
    }

    @Inject
    lateinit var db: RoomService

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Note>) {
        when(type) {
            0 -> db.getRange(params, callback)
            1 -> db.getRangeFilterMoment(afterSleep, params, callback)
            2 -> db.getRangeFilterPeriod(beginPeriod, params, callback)
            3 -> db.getRangeFilterAll(beginPeriod, afterSleep, params, callback)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Note>) {
        when(type) {
            0 -> db.getFirstPage(params, callback)
            1 -> db.getFirstPageFilterMoment(afterSleep, params, callback)
            2 -> db.getFirstPageFilterPeriod(beginPeriod, params, callback)
            3 -> db.getFirstPageFilterAll(beginPeriod, afterSleep, params, callback)
        }
    }
}