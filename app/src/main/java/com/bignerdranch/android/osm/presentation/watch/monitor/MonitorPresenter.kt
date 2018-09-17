package com.bignerdranch.android.osm.presentation.watch.monitor

import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.presentation.watch.core.BluetoothService
import com.hannesdorfmann.mosby3.PresenterManager.getViewState
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class MonitorPresenter: MvpBasePresenter<MonitorContract.View>(), MonitorContract.Presenter {
    var bluetoothService: BluetoothService = BluetoothService()

    init {
        bluetoothService.getMessage(App.appBluetoothSocket, { msg -> view!!.setPulse(msg) })
    }
}