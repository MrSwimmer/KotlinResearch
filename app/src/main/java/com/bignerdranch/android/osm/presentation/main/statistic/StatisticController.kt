package com.bignerdranch.android.osm.presentation.main.statistic

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.charts.ValueLineChart
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.models.ValueLineSeries
import javax.annotation.Nullable
import kotlin.collections.ArrayList

class StatisticController : MvpController<StatisticContract.View, StatisticContract.Presenter>(), StatisticContract.View {

    private var currentPeriod = 4
    private var moment = 2

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
    @BindView(R.id.statistic_sleep_image)
    lateinit var sleepImage: ImageView
    @BindView(R.id.statistic_train_image)
    lateinit var trainImage: ImageView
    @BindView(R.id.statistic_all_image)
    lateinit var allImage: ImageView

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
        lineChart.clearChart()
        lineChart.addSeries(series)
        lineChart.startAnimation()
    }

    override fun setPieChart(pieSeries: ArrayList<PieModel>) {
        pieChart.clearChart()
        pieSeries.forEach {
            pieChart.addPieSlice(it)
        }
        pieChart.startAnimation()
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        updateData()
    }

    private fun updateMoment(moment: Int) {
        sleepImage.setImageResource(R.drawable.ic_alarm_grey)
        trainImage.setImageResource(R.drawable.ic_training_grey)
        allImage.setImageResource(R.drawable.ic_moment_all_grey)
        when (moment) {
            0 -> sleepImage.setImageResource(R.drawable.ic_alarm_red)
            1 -> trainImage.setImageResource(R.drawable.ic_training_red)
            2 -> allImage.setImageResource(R.drawable.ic_moment_all_red)
        }
    }

    @Nullable
    @OnClick(R.id.statistic_period_all)
    fun onPeriodAllClick() {
        Log.i("code", periodAll.textSize.toString())
        if (periodAll.textSize != 42.0f) {
            currentPeriod = 0
            updateData()
        }
    }

    @Nullable
    @OnClick(R.id.statistic_period_year)
    fun onPeriodYearClick() {
        if (periodYear.textSize != 42.0f) {
            currentPeriod = 1
            updateData()
        }
    }

    @Nullable
    @OnClick(R.id.statistic_period_month)
    fun onPeriodMonthClick() {
        if (periodMonth.textSize != 42.0f) {
            currentPeriod = 2
            updateData()
        }
    }

    @Nullable
    @OnClick(R.id.statistic_period_week)
    fun onPeriodWeekClick() {
        if (periodWeek.textSize != 42.0f) {
            currentPeriod = 3
            updateData()
        }
    }

    @Nullable
    @OnClick(R.id.statistic_train_image)
    fun onTrainClick() {
        moment = 1
        updateData()
    }

    @Nullable
    @OnClick(R.id.statistic_sleep_image)
    fun onSleepClick() {
        moment = 0
        updateData()
    }

    @Nullable
    @OnClick(R.id.statistic_all_image)
    fun onAllClick() {
        moment = 2
        updateData()
    }

    @SuppressLint("ResourceAsColor")
    private fun updatePeriod(period: Int) {
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
            3, 4 -> {
                periodWeek.setTextColor(red)
                periodWeek.textSize = 21f
            }
        }
    }

    private fun updateData() {
        updateMoment(moment)
        updatePeriod(currentPeriod)
        if (currentPeriod == 4)
            presenter.getDataForChart(currentPeriod - 1, moment)
        else
            presenter.getDataForChart(currentPeriod, moment)
    }
}