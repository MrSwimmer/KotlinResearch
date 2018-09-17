package com.bignerdranch.android.osm.presentation.watch.list

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bignerdranch.android.osm.R
import com.bignerdranch.android.osm.presentation.watch.core.BluetoothService
import com.bignerdranch.android.osm.presentation.watch.list.recycler.BTDevices
import com.bignerdranch.android.osm.presentation.watch.list.recycler.RecyclerViewBTAdapter
import com.bignerdranch.android.osm.presentation.watch.monitor.MonitorController
import com.bluelinelabs.conductor.RouterTransaction
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import kotlinx.android.synthetic.main.controller_list.view.*

class ListController : MvpController<ListContract.View, ListContract.Presenter>(), ListContract.View {
    override fun createPresenter(): ListContract.Presenter {
        return ListPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_list, container, false)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.setList()
    }

    override fun showMessage(s: String) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show()
    }

    override fun nextFragment(success: Boolean, bundle: Bundle?) {
        if (success) {
            router.replaceTopController(RouterTransaction.with(MonitorController()))
        } else {
            Log.i("code", "error success")
        }
    }

    override fun updateRecycler(adapter: RecyclerViewBTAdapter) {
        val linearLayoutManager = LinearLayoutManager(activity)
        val itemAnimator = DefaultItemAnimator()
        view!!.recyclerView.adapter = adapter
        view!!.recyclerView.layoutManager = linearLayoutManager
        view!!.recyclerView.itemAnimator = itemAnimator
    }

    override fun unclickableRecycler() {
        view!!.recyclerView.isClickable = false
        view!!.recyclerView.visibility = View.GONE
        view!!.progressBar.visibility = View.VISIBLE
    }
}