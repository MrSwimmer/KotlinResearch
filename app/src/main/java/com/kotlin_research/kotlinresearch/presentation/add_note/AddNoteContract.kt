package com.kotlin_research.kotlinresearch.presentation.add_note

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.kotlin_research.kotlinresearch.data.room.Note

interface AddNoteContract {
    interface View : MvpView {
        fun setRes(points: Double, zone: Int)
        fun gotoResult(note: Note)
    }

    interface Presenter : MvpPresenter<View> {
        fun getResult(pulseSitting: String, pulseStanding: String)
        fun addNote(note: Note)
    }
}