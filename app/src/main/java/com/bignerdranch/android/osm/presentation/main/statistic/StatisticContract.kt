package com.bignerdranch.android.osm.presentation.main.statistic

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.models.ValueLineSeries

interface StatisticContract {
    interface View : MvpView {
        fun setLineChartData(series: ValueLineSeries)
        fun setPieChart(pieSeries: ArrayList<PieModel>)
    }

    interface Presenter : MvpPresenter<View> {
        fun getDataForChart(period: Int, afterSleep: Int)
    }
}