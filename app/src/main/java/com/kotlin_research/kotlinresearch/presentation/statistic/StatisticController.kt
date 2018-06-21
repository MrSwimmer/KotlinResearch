package com.kotlin_research.kotlinresearch.presentation.statistic

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.charts.ValueLineChart
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.models.ValueLineSeries
import kotlin.collections.ArrayList

class StatisticController : MvpController<StatisticContract.View, StatisticContract.Presenter>(), StatisticContract.View {

    private var currentPeriod = 4

    @BindView(R.id.statistic_line_chart)
    lateinit var lineChart: ValueLineChart
    @BindView(R.id.statistic_pie_chart)
    lateinit var pieChart: PieChart

    @BindView(R.id.statistic_period_all)
    lateinit var periodAll: TextView
    @BindView(R.id.statistic_period_year)
    lateinit var periodYear: TextView
    @BindView(R.id.statistic_period_month)
    lateinit var periodMonth: TextView
    @BindView(R.id.statistic_period_week)
    lateinit var periodWeek: TextView


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

    override fun setPieChart(pieSeries: ArrayList<PieModel>) {
        pieSeries.forEach {
            pieChart.addPieSlice(it)
        }
        pieChart.startAnimation()
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        updatePeriod(3)
    }

    @OnClick(R.id.statistic_period_all)
    fun onPeriodAllClick() {
        updatePeriod(0)
    }

    @OnClick(R.id.statistic_period_year)
    fun onPeriodYearClick() {
        updatePeriod(1)
    }

    @OnClick(R.id.statistic_period_month)
    fun onPeriodMonthClick() {
        updatePeriod(2)
    }

    @OnClick(R.id.statistic_period_week)
    fun onPeriodWeekClick() {
        updatePeriod(3)
    }

    @SuppressLint("ResourceAsColor")
    private fun updatePeriod(period: Int) {
        if (currentPeriod != period) {
            currentPeriod = period
            val grey = Color.parseColor("#848484")
            val red = Color.parseColor("#d7443c")
            periodAll.setTextColor(grey)
            periodYear.setTextColor(grey)
            periodMonth.setTextColor(grey)
            periodWeek.setTextColor(grey)
            periodAll.textSize = 18f
            periodYear.textSize = 18f
            periodMonth.textSize = 18f
            periodWeek.textSize = 18f
            when (period) {
                0 -> {
                    periodAll.setTextColor(red)
                    periodAll.textSize = 21f
                }
                1 -> {
                    periodYear.setTextColor(red)
                    periodYear.textSize = 21f
                }
                2 -> {
                    periodMonth.setTextColor(red)
                    periodMonth.textSize = 21f
                }
                3 -> {
                    periodWeek.setTextColor(red)
                    periodWeek.textSize = 21f
                }
            }
            presenter.getDataForChart(period)
        }
    }
}