package com.bignerdranch.android.osm.presentation.watch.monitor

import android.os.Bundle
import com.bignerdranch.android.osm.presentation.watch.list.recycler.RecyclerViewBTAdapter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface MonitorContract {
    interface View : MvpView {
        fun setPulse(msg: String?)
    }

    interface Presenter : MvpPresenter<View> {
    }
}