package com.bignerdranch.android.osm.presentation.watch.monitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.osm.R
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import kotlinx.android.synthetic.main.controller_monitor.view.*

class MonitorController: MvpController<MonitorContract.View, MonitorContract.Presenter>(), MonitorContract.View {
    override fun createPresenter(): MonitorContract.Presenter {
        return MonitorPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_monitor, container, false)
        return view
    }

    override fun setPulse(msg: String?) {
    }
}