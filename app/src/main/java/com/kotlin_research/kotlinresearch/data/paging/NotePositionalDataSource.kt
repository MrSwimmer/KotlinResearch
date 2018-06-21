package com.kotlin_research.kotlinresearch.data.paging

import android.arch.paging.PositionalDataSource
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.domain.RoomService
import javax.inject.Inject

class NotePositionalDataSource : PositionalDataSource<Note>() {

    init {
        App.getComponent().inject(this)
    }

    @Inject
    lateinit var db: RoomService

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Note>) {
        db.getRange(params, callback)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Note>) {
        db.getFirstPage(params, callback)
    }
}