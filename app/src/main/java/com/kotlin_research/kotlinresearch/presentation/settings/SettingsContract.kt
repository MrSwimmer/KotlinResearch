package com.kotlin_research.kotlinresearch.presentation.settings

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface SettingsContract {
    interface View : MvpView
    interface Presenter : MvpPresenter<View> {
        fun deleteAll()
    }
}