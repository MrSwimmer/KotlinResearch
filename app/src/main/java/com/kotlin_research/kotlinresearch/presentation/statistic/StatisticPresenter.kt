package com.kotlin_research.kotlinresearch.presentation.statistic

import android.graphics.Color
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.domain.RoomService
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class StatisticPresenter : MvpBasePresenter<StatisticContract.View>(), StatisticContract.Presenter {

    @Inject
    lateinit var roomService: RoomService

    init {
        App.getComponent().inject(this)
    }

    override fun getDataForChart(period: Int) {
        roomService.getInterval(Date().time, getLongInterval(period), object : RoomService.NotesCallback {
            override fun onSuccess(notes: List<Note>) {
                val lineSeries = ValueLineSeries()
                val zones = Array(4, { 0 })
                lineSeries.color = Color.parseColor("#d7443c")
                lineSeries.widthOffset = 0.5f
                notes.forEach {
                    val date = Date(it.date)
                    val day = date.day
                    val month = date.month
                    val sDay: String
                    val sMonth: String
                    zones[it.zone - 1]++
                    sDay = if (day < 10)
                        "0$day"
                    else
                        "$day"
                    sMonth = if (month < 10)
                        "0$month"
                    else
                        "$month"
                    lineSeries.addPoint(ValueLinePoint("$sDay.$sMonth", it.points.toFloat()))
                }
                val pieSeries: ArrayList<PieModel> = ArrayList()
                pieSeries.add(PieModel("I", zones[0].toFloat(), Color.parseColor("#c0e637")))
                pieSeries.add(PieModel("II", zones[1].toFloat(), Color.parseColor("#55bbeb")))
                pieSeries.add(PieModel("III", zones[2].toFloat(), Color.parseColor("#f0d73c")))
                pieSeries.add(PieModel("IV", zones[3].toFloat(), Color.parseColor("#d7443c")))
                view.setPieChart(pieSeries)
                view.setLineChartData(lineSeries)
            }

            override fun onError(e: Throwable) {
                Log.i("code", "error get Week " + e.message)
            }
        })
    }

    private fun getLongInterval(period: Int): Long {
        when (period) {
            0 -> return -1
            1 -> return 31536000000
            2 -> return 2592000000
            3 -> return 604800000
        }
        return 604800000
    }
}