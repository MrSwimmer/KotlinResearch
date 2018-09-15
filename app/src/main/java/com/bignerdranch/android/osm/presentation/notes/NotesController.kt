package com.bignerdranch.android.osm.presentation.settings

import android.arch.paging.PagedList
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.R
import com.bignerdranch.android.osm.presentation.notes.paging.NoteDiffUtilCallback
import com.bignerdranch.android.osm.data.room.Note
import com.bignerdranch.android.osm.presentation.add_note.AddNoteController
import com.bignerdranch.android.osm.presentation.notes.recycler.NotePagingAdapter
import javax.annotation.Nullable

class NotesController : MvpController<NotesContract.View, NotesContract.Presenter>(), NotesContract.View {

    var filter = false
    private var currentPeriod = 4
    private var moment = 2

    @BindView(R.id.notes_recycler)
    lateinit var recyclerView: RecyclerView
    @Nullable
    @BindView(R.id.notes_fab)
    lateinit var floatingActionButton: FloatingActionButton

    //filter layout
    @BindView(R.id.notes_filter_layout)
    lateinit var filterLayout: ConstraintLayout
    @BindView(R.id.notes_period_all)
    lateinit var periodAll: TextView
    @BindView(R.id.notes_period_year)
    lateinit var periodYear: TextView
    @BindView(R.id.notes_period_month)
    lateinit var periodMonth: TextView
    @BindView(R.id.notes_period_week)
    lateinit var periodWeek: TextView
    @BindView(R.id.notes_all_image)
    lateinit var momentAll: ImageView
    @BindView(R.id.notes_sleep_image)
    lateinit var momentSleep: ImageView
    @BindView(R.id.notes_train_image)
    lateinit var momentTrain: ImageView

    //panel open filter panel
    @BindView(R.id.notes_show_hide_image)
    lateinit var arrowImage: ImageView

    override fun createPresenter(): NotesContract.Presenter {
        return NotesPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_notes, container, false)
        ButterKnife.bind(this, view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        App.getComponent().inject(this)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        updateData()
    }

    @OnClick(R.id.notes_fab)
    fun onFABClick() {
        router.pushController(RouterTransaction.with(AddNoteController()))
    }

    override fun setAdapter(pagedList: PagedList<Note>) {
        val adapter = NotePagingAdapter(NoteDiffUtilCallback(), router)
        adapter.submitList(pagedList)
        recyclerView.adapter = adapter
    }

    private fun updateMoment(moment: Int) {
        momentSleep.setImageResource(R.drawable.ic_alarm_grey)
        momentTrain.setImageResource(R.drawable.ic_training_grey)
        momentAll.setImageResource(R.drawable.ic_moment_all_grey)
        when (moment) {
            0 -> momentSleep.setImageResource(R.drawable.ic_alarm_red)
            1 -> momentTrain.setImageResource(R.drawable.ic_training_red)
            2 -> momentAll.setImageResource(R.drawable.ic_moment_all_red)
        }
    }

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

    @OnClick(R.id.notes_period_all)
    fun onPeriodAllClick() {
        Log.i("code", periodAll.textSize.toString())
        if (periodAll.textSize != 42.0f) {
            currentPeriod = 0
            updateData()
        }
    }

    @OnClick(R.id.notes_period_year)
    fun onPeriodYearClick() {
        if (periodYear.textSize != 42.0f) {
            currentPeriod = 1
            updateData()
        }
    }

    @OnClick(R.id.notes_period_month)
    fun onPeriodMonthClick() {
        if (periodMonth.textSize != 42.0f) {
            currentPeriod = 2
            updateData()
        }
    }

    @OnClick(R.id.notes_period_week)
    fun onPeriodWeekClick() {
        if (periodWeek.textSize != 42.0f) {
            currentPeriod = 3
            updateData()
        }
    }

    @OnClick(R.id.notes_train_image)
    fun onTrainClick() {
        moment = 1
        updateData()
    }

    @OnClick(R.id.notes_sleep_image)
    fun onSleepClick() {
        moment = 0
        updateData()
    }

    @OnClick(R.id.notes_all_image)
    fun onAllClick() {
        moment = 2
        updateData()
    }

    private fun updateData() {
        updateMoment(moment)
        updatePeriod(currentPeriod)
        if (currentPeriod == 4)
            presenter.setRecyclerData(currentPeriod - 1, moment)
        else
            presenter.setRecyclerData(currentPeriod, moment)
    }

    @OnClick(R.id.notes_show_hide_filter)
    fun onShowFilterClick() {
        if (filter) {
            filterLayout.visibility = View.GONE
            arrowImage.setImageResource(R.drawable.ic_arrow_open_filter)
        }
        else {
            filterLayout.visibility = View.VISIBLE
            arrowImage.setImageResource(R.drawable.ic_arrow_filter_close)
        }

        filter = !filter
    }
}