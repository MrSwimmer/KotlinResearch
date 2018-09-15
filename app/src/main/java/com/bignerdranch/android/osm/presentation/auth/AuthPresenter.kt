package com.bignerdranch.android.osm.presentation.auth

import com.bignerdranch.android.osm.App
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter

class AuthPresenter: MvpBasePresenter<AuthContract.View>(), AuthContract.Presenter {
    init {
        App.getComponent().inject(this)
    }
}