package com.bignerdranch.android.osm.presentation.sign_in

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface SignInContract {
    interface View : MvpView
    interface Presenter : MvpPresenter<View>
}