package com.bignerdranch.android.osm.presentation.main.settings

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface SettingsContract {
    interface View : MvpView
    interface Presenter : MvpPresenter<View> {
        fun deleteAll()
    }
}