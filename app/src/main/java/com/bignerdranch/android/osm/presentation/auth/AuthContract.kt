package com.bignerdranch.android.osm.presentation.auth

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.models.ValueLineSeries

interface AuthContract {
    interface View : MvpView {
    }

    interface Presenter : MvpPresenter<View> {
    }
}