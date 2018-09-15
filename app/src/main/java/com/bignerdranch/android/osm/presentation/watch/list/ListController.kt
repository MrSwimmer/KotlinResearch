package com.bignerdranch.android.osm.presentation.watch.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import com.bignerdranch.android.osm.R
import com.bignerdranch.android.osm.presentation.watch.core.BluetoothService
import com.bignerdranch.android.osm.presentation.watch.list.recycler.RecyclerViewBTAdapter
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController

class ListController: MvpController<ListContract.View, ListContract.Presenter>(), ListContract.View, BluetoothService.BluetoothServiceCallback, RecyclerViewBTAdapter.RecyclerCallback {
    override fun createPresenter(): ListContract.Presenter {
        return ListPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_list, container, false)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)

    }


}