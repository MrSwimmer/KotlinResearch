package com.bignerdranch.android.osm.presentation.auth.sign_in

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface CloudContract {
    interface View : MvpView
    interface Presenter : MvpPresenter<View>
}