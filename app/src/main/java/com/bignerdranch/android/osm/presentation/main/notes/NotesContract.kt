package com.bignerdranch.android.osm.presentation.settings

import android.arch.paging.PagedList
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.bignerdranch.android.osm.data.room.Note

interface NotesContract {
    interface View : MvpView {
        fun setAdapter(pagedList: PagedList<Note>)
    }

    interface Presenter : MvpPresenter<View> {
        fun setRecyclerData(currentPeriod: Int, moment: Int)
    }
}