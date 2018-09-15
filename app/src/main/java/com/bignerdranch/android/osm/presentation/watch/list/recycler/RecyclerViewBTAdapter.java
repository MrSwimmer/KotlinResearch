package com.bignerdranch.android.osm.presentation.watch.list.recycler;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.osm.R;

import java.util.List;

public class RecyclerViewBTAdapter extends RecyclerView.Adapter<RecyclerViewBTAdapter.MyViewHolder> {
    private List<BTDevices> devicesList;

    public interface RecyclerCallback {
        void onItemClick(BluetoothDevice bluetoothDevice);
    }

    private RecyclerCallback recyclerCallback;

    public RecyclerViewBTAdapter(List<BTDevices> devicesList) {
        this.devicesList = devicesList;

    }

    public void registerRecyclerCallback(RecyclerCallback recyclerCallback) {
        this.recyclerCallback = recyclerCallback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BTDevices btdevice = devicesList.get(position);
        holder.nameTextView.setText(btdevice.getName());
        holder.addressTextView.setText(btdevice.getAdress());
        holder.itemView.setOnClickListener(view -> recyclerCallback.onItemClick(btdevice.getDevice()));
    }

    @Override
    public int getItemCount() {
        return devicesList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView addressTextView;

        MyViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewLarge);
            addressTextView = itemView.findViewById(R.id.textViewSmall);
        }
    }
}