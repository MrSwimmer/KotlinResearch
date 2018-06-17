package com.kotlin_research.kotlinresearch.presentation.settings

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.kotlin_research.kotlinresearch.data.room.Note

interface NotesContract {
    interface View : MvpView {
        fun initAdapter(notes: List<Note>)
    }

    interface Presenter : MvpPresenter<View> {
        fun setRecyclerData()
    }
}