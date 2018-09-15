package com.bignerdranch.android.osm.presentation.watch.list.recycler;

import android.bluetooth.BluetoothDevice;

public class BTDevices {
    private String name;
    private String adress;
    private BluetoothDevice device;

    public BTDevices(String name, String adress, BluetoothDevice device){
        this.name = name;
        this.adress = adress;
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }
}
