package com.kotlin_research.kotlinresearch.presentation.add_note

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface AddNoteContract {
    interface View : MvpView {
        fun setRes(points: Double, zone: Int)
    }

    interface Presenter : MvpPresenter<View> {
        fun getResult(pulseSitting: String, pulseStanding: String)
    }
}