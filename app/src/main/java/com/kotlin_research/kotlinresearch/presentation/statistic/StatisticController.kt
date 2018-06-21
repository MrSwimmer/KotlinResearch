package com.kotlin_research.kotlinresearch.presentation.statistic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R
import org.eazegraph.lib.charts.ValueLineChart
import org.eazegraph.lib.models.ValueLineSeries
import java.util.*


class StatisticController : MvpController<StatisticContract.View, StatisticContract.Presenter>(), StatisticContract.View {

    @BindView(R.id.statistic_line_chart)
    lateinit var lineChart: ValueLineChart

    override fun createPresenter(): StatisticContract.Presenter {
        return StatisticPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_statistic, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        return view
    }

    override fun setLineChartData(series: ValueLineSeries) {
        lineChart.addSeries(series)
        lineChart.startAnimation()
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.getDataForLineChart()
    }
}