package com.kotlin_research.kotlinresearch.presentation.statistic

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface StatisticContract {
    interface View : MvpView
    interface Presenter : MvpPresenter<View>
}