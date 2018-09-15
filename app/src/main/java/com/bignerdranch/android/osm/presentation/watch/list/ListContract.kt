package com.bignerdranch.android.osm.presentation.watch.list

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface ListContract {
    interface View : MvpView {
        fun showMessage(s: String)
    }

    interface Presenter : MvpPresenter<View> {
    }
}