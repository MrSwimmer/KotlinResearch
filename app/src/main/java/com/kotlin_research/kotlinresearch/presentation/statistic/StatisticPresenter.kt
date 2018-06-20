package com.kotlin_research.kotlinresearch.presentation.statistic

import android.graphics.Color
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.domain.RoomService
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries
import java.util.*
import javax.inject.Inject

class StatisticPresenter : MvpBasePresenter<StatisticContract.View>(), StatisticContract.Presenter {
    val INTERVAL_YEAR = 1
    val INTERVAL_MONTH = 1
    private val INTERVAL_WEEK: Long = 604800000

    @Inject
    lateinit var roomService: RoomService

    init {
        App.getComponent().inject(this)
    }

    override fun getDataForLineChart() {

        roomService.getInterval(Date().time, INTERVAL_WEEK, object : RoomService.StatCallback {
            override fun onSuccess(notes: List<Note>) {
                val series = ValueLineSeries()
                series.color = R.color.main_red
                notes.forEach{
                    val date = Date(it.date)
                    val day = date.day
                    val month = date.month
                    series.addPoint(ValueLinePoint("$day.$month", it.points.toFloat()))
                }
                view.setLineChartData(series)
            }

            override fun onError(e: Throwable) {
                Log.i("code", "error get Week " + e.message)
            }
        })
    }
}