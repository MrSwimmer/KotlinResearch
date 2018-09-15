package com.bignerdranch.android.osm.presentation.watch.list

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.R
import com.bignerdranch.android.osm.presentation.watch.core.BluetoothService
import com.bignerdranch.android.osm.presentation.watch.list.recycler.BTDevices
import com.bignerdranch.android.osm.presentation.watch.list.recycler.RecyclerViewBTAdapter
import com.hannesdorfmann.mosby3.PresenterManager.getViewState
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import java.util.ArrayList

class ListPresenter : MvpBasePresenter<ListContract.View>(), ListContract.Presenter, BluetoothService.BluetoothServiceCallback, RecyclerViewBTAdapter.RecyclerCallback {
    private val DISCOVERY_REQUEST = 123
    lateinit var bluetoothService: BluetoothService

    init {
        if (App.appBluetoothSocket == null) {
            bluetoothService = BluetoothService()
            bluetoothService.registerBluetoothServiceCallBack(this)
            if (bluetoothService.isEnabled()) {
                bluetoothService.turnOn()
                view!!.showMessage("Bluetooth module have activated!")
                bluetoothService.searchDevice()
            } else {
                view!!.showMessage("Bluetooth module haven't been detected!")
            }
        } else {
            view!!.nextFragment(true, null)
        }
    }

    override fun recyclerCallingBack(btDevicesArrayList: ArrayList<BTDevices>) {
        val adapter = RecyclerViewBTAdapter(btDevicesArrayList)
        adapter.registerRecyclerCallback(this)
        getViewState().updateRecycler(adapter)

    }

    override fun successConnectingCallingBack(bluetoothDevice: BluetoothDevice) {
        val bundle = Bundle()
        bundle.putString("nameDevice", bluetoothDevice.name)
        getViewState().nextFragment(true, bundle)
        Log.i("TAG", " success calling back")
    }

    override fun failedConnectingCallingBack(bluetoothDevice: BluetoothDevice) {
        val bundle = Bundle()
        bundle.putString("nameDevice", bluetoothDevice.name)
        getViewState().nextFragment(false, bundle)
    }

    override fun onItemClick(bluetoothDevice: BluetoothDevice) {
        getViewState().unclickableRecycler()
        Log.i("TAG", "try connect")
        bluetoothService.connect(bluetoothDevice)
    }
}