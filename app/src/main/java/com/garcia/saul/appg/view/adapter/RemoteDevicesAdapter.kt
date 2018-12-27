package com.garcia.saul.appg.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.garcia.saul.appg.R
import com.garcia.saul.appg.data.model.MBluetoothDevice
import com.garcia.saul.appg.view.listener.RecyclerDeviceListener
import kotlinx.android.synthetic.main.cardview_remote_item.view.*

class RemoteDevicesAdapter(private var devices: List<MBluetoothDevice>, private val listener: RecyclerDeviceListener)
    : RecyclerView.Adapter<RemoteDevicesAdapter.ViewHolder>() {

    private var sortBy: Boolean = true

    override fun getItemCount() = devices.size

    override fun onBindViewHolder(holder: RemoteDevicesAdapter.ViewHolder, position: Int) = holder.bind(devices[position], listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteDevicesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_remote_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(device: MBluetoothDevice, listener: RecyclerDeviceListener)= with(itemView){

            deviceName.text = device.name
            createdAt.text = device.createdAt
            deviceStrength.text = device.strength

            setOnClickListener{(listener.onClick(device,adapterPosition))}
        }

    }

    fun sort() {

        devices = if (sortBy) {
            devices.sortedBy { it -> it.createdAt }
        } else {
            devices.sortedByDescending { it -> it.createdAt }
        }
        sortBy = !sortBy

        notifyDataSetChanged()
    }

}