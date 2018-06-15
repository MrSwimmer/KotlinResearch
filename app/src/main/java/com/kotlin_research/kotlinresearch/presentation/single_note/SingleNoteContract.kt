package com.kotlin_research.kotlinresearch.presentation.single_note

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface SingleNoteContract {
    interface View : MvpView
    interface Presenter : MvpPresenter<View>
}