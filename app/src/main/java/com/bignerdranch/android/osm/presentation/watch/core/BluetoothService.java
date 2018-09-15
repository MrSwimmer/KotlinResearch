package com.bignerdranch.android.osm.presentation.watch.core;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.Log;

import com.bignerdranch.android.osm.App;
import com.bignerdranch.android.osm.presentation.watch.list.recycler.BTDevices;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class BluetoothService {
    public interface BluetoothServiceCallback{
        void recyclerCallingBack(ArrayList<BTDevices> btDevicesArrayList);
        void successConnectingCallingBack(BluetoothDevice device);
        void failedConnectingCallingBack(BluetoothDevice device);
    }
    public interface getMessageCallBack{
        void successMessage(String msg);
    }
    private BluetoothServiceCallback bluetoothServiceCallback;
    private BluetoothAdapter bluetoothAdapter;
    private App app;
    private UUID uuid = UUID.fromString("056776ca-8ff1-11e8-9eb6-529269fb1459");
    private static ArrayList<BTDevices> btDevicesList = new ArrayList<>();
    private static ArrayList<String> nameDevicesList = new ArrayList<>();
    private final BroadcastReceiver deviceFoundReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (nameDevicesList.contains(device.getName())){
                    btDevicesList.set(nameDevicesList.indexOf(device.getName()), new BTDevices(device.getName(), device.getAddress(), device));
                }
                else{
                    btDevicesList.add(new BTDevices(device.getName(),device.getAddress(), device));
                    nameDevicesList.add(device.getName());
                }
                bluetoothServiceCallback.recyclerCallingBack(btDevicesList);
                Log.i("TAG",device.getAddress()+" "+device.getName());
                Log.i("TAG", btDevicesList.size()+"");
            }

        }
    };
    public BluetoothService(){
        app = App.instance;
    }
    public static void turnOff(){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.disable();
    }
    public void registerBluetoothServiceCallBack(BluetoothServiceCallback callback){
        this.bluetoothServiceCallback = callback;
    }
    public boolean isEnabled(){
        boolean isEnabled;
        if (app.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)){
            isEnabled = true;
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }else{
            isEnabled = false;
        }
        return isEnabled;
    }
    public void turnOn(){
        bluetoothAdapter.enable();
    }

    public void searchDevice() {
        app.registerReceiver(deviceFoundReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        bluetoothAdapter.startDiscovery();
    }

    public void connect(BluetoothDevice device) {
        Log.i("TAG", "touch");
        final BluetoothSocket socket;
        bluetoothAdapter.cancelDiscovery();
        BluetoothSocket presocket = null;
        try {
            presocket = device.createRfcommSocketToServiceRecord(uuid);
            Log.i("TAG", "Socket's create() method success");
        } catch (IOException e) {
            Log.i("TAG", "Socket's create() method failed", e);
            bluetoothServiceCallback.failedConnectingCallingBack(device);
        }
        socket = presocket;
        Single.create((SingleEmitter<BluetoothSocket> emitter) -> {
            Log.i("TAG", "trying to connect with "+device.getName());
            try {
                socket.connect();
                if (socket.isConnected()) Log.i("TAG", "socket not null");
                emitter.onSuccess(socket);
                Log.i("TAG", "Success connecting");
            } catch (IOException connectException) {
                Log.i("TAG", "failed connecting");
                try {
                    socket.close();
                    emitter.onError(connectException);
                } catch (IOException closeException) {
                    Log.i("TAG", "Could not close the client socket", closeException);
                }
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<BluetoothSocket>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(BluetoothSocket bluetoothSocket) {
                Log.i("TAG","OnSuccess");
                App.appBluetoothSocket=bluetoothSocket;
                bluetoothServiceCallback.successConnectingCallingBack(device);

            }

            @Override
            public void onError(Throwable e) {
                bluetoothServiceCallback.failedConnectingCallingBack(device);
            }
        });
    }

    public void getMessage(BluetoothSocket socket, getMessageCallBack callBack){
        if (socket!=null) {
            final InputStream inputStream;
            InputStream tmpIn = null;
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Log.i("TAG", "Error occurred when creating input stream", e);
            }
            inputStream = tmpIn;
            io.reactivex.Observable.create((ObservableOnSubscribe<String>) emitter -> {
                byte[] mmBuffer = new byte[1024];
                int numBytes = 0;
                while (true) {
                    if (socket.isConnected()) {
                        try {
                            numBytes = inputStream.read(mmBuffer);
                            if (numBytes > 0) {
                                String strmsg = new String(mmBuffer);
                                strmsg = strmsg.substring(0, numBytes);
                                emitter.onNext(strmsg);
                            }
                        } catch (IOException e) {
                            socket.close();

                            Log.i("TAG", "Input stream was disconnected", e);
                            break;
                        }
                    } else {
                        socket.close();

                    }
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(String s) {
                    callBack.successMessage(s);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                    Log.i("TAG", "Complete");
                }
            });
        }
    }



}
