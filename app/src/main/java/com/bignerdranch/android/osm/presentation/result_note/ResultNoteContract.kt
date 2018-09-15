package com.bignerdranch.android.osm.presentation.result_note

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface ResultNoteContract {
    interface View : MvpView
    interface Presenter : MvpPresenter<View>
}