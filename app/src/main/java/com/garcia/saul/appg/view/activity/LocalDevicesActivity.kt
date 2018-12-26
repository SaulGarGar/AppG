package com.garcia.saul.appg.view.activity

import android.app.Dialog
import android.bluetooth.BluetoothAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.garcia.saul.appg.R

class LocalDevicesActivity : AppCompatActivity() {

    private val columns = 1
    private val ENABLE_BT_REQUEST_CODE = 1
    private val REQUEST_COARSE_LOCATION = 999
    private lateinit var dialogBlockSearchUi: Dialog
    private var bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_devices)

    }


}
