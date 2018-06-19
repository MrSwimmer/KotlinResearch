package com.kotlin_research.kotlinresearch.presentation.statistic

import android.graphics.Color
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries

class StatisticPresenter : MvpBasePresenter<StatisticContract.View>(), StatisticContract.Presenter {

    init {
        App.getComponent().inject(this)
    }

    override fun getDataForLineChart() {
        var series = ValueLineSeries()
        series.color = R.color.main_red
        series.addPoint(ValueLinePoint("1", 2.4f))
        series.addPoint(ValueLinePoint("2", 1.4f))
        series.addPoint(ValueLinePoint("3", 3f))
        series.addPoint(ValueLinePoint("4", 6f))
        series.addPoint(ValueLinePoint("5", 8f))
        series.addPoint(ValueLinePoint("6", 4f))
        series.addPoint(ValueLinePoint("7", 6f))
        series.addPoint(ValueLinePoint("8", 2f))
        series.addPoint(ValueLinePoint("9", 1f))
        view.setLineChartData(series)
    }
}