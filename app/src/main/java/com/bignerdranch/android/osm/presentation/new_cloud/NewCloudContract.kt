package com.bignerdranch.android.osm.presentation.new_cloud

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface NewCloudContract {
    interface View : MvpView
    interface Presenter : MvpPresenter<View> {
        fun checkCloud()
    }
}