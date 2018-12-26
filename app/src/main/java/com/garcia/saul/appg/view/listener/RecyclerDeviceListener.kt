package com.garcia.saul.appg.view.listener

import com.garcia.saul.appg.data.model.MBluetoothDevice

interface RecyclerDeviceListener {
    fun onClick(mBluetoothDevice: MBluetoothDevice, position: Int)
}