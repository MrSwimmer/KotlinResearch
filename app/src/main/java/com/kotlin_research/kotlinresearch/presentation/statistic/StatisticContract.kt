package com.kotlin_research.kotlinresearch.presentation.statistic

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import org.eazegraph.lib.models.ValueLineSeries

interface StatisticContract {
    interface View : MvpView {
        fun setLineChartData(series: ValueLineSeries)
    }

    interface Presenter : MvpPresenter<View> {
        fun getDataForLineChart()
    }
}