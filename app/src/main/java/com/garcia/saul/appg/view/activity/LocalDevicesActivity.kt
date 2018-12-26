package com.garcia.saul.appg.view.activity

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.garcia.saul.appg.R
import com.garcia.saul.appg.data.model.MBluetoothDevice
import com.garcia.saul.appg.presenter.BtDevicesPresenter
import com.garcia.saul.appg.view.adapter.BluetoothDevicesAdapter
import com.garcia.saul.appg.view.listener.RecyclerDeviceListener
import kotlinx.android.synthetic.main.activity_local_devices.*

class LocalDevicesActivity : AppCompatActivity(), BtDevicesPresenter.View {

    private val ENABLE_BT_REQUEST_CODE = 1
    private val REQUEST_COARSE_LOCATION = 999
    private var bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    private var devicesList  = arrayListOf<MBluetoothDevice>()

    private lateinit var devicesRecycler : RecyclerView
    private lateinit var adapter: BluetoothDevicesAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_devices)

        listener()
        disableRefresh()
        verifyBluetooth()
        checkLocationPermission()

    }


    private fun verifyBluetooth() {
        if (bluetoothAdapter.isEnabled) {
            bluetoothSwitch.isChecked = true
            bluetoothSwitch.isClickable = false
            refreshFab.isEnabled = true
        }
    }

    private fun disableRefresh() {
        refreshFab.isEnabled = false
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_COARSE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_COARSE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    proceedDiscovery()
                } else {
                    checkLocationPermission()
                }
            }
        }
    }

    private fun listener() {

        bluetoothSwitch.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                enableBluetooth()

            }

        }

        listFab.setOnClickListener{
            val intent = Intent(this, RemoteDevicesActivity::class.java)
            startActivity(intent)
        }

        refreshFab.setOnClickListener {

            devicesList.clear()

            if (bluetoothAdapter.startDiscovery()) {
                Toast.makeText(applicationContext, applicationContext.resources.getString(R.string.on_search_devices), Toast.LENGTH_SHORT).show()
                proceedDiscovery()
            } else {

                Toast.makeText(applicationContext, applicationContext.resources.getString(R.string.on_error), Toast.LENGTH_SHORT).show()
            }
        }


    }


    private var broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothDevice.ACTION_FOUND == action) {

                val rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, java.lang.Short.MIN_VALUE)
                val bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                val name: String = bluetoothDevice.name ?: applicationContext.resources.getString(R.string.device_unknown)
                val strength = rssi.toString() + applicationContext.resources.getString(R.string.device_dbm)
                val device = MBluetoothDevice("",name,bluetoothDevice.address,strength,"")
                devicesList.add(device)
                Log.w("Device", name)

                setRecyclerView()
            }
        }
    }

    private fun enableBluetooth() {
        if (!bluetoothAdapter.isEnabled) {
            val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableIntent, ENABLE_BT_REQUEST_CODE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(broadcastReceiver)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ENABLE_BT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                bluetoothSwitch.isClickable = false
                refreshFab.isEnabled = true
                Toast.makeText(applicationContext, applicationContext.resources.getString(R.string.bluetooth_activated), Toast.LENGTH_SHORT).show()
            }

            if (resultCode == RESULT_CANCELED) {
                bluetoothSwitch.isChecked = false
                Toast.makeText(applicationContext, applicationContext.resources.getString(R.string.bluetooth_disabled), Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun proceedDiscovery() {
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        filter.addAction(BluetoothDevice.ACTION_NAME_CHANGED)
        registerReceiver(broadcastReceiver, filter)
        bluetoothAdapter.startDiscovery()
    }

    private fun setRecyclerView(){

        devicesRecycler = recyclerView as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = layoutManager
        adapter = (BluetoothDevicesAdapter(devicesList, object: RecyclerDeviceListener{
            override fun onClick(mBluetoothDevice: MBluetoothDevice, position: Int) {
                Toast.makeText(this@LocalDevicesActivity, "Device ${mBluetoothDevice.name}",Toast.LENGTH_SHORT).show()
            }
        }))
        recyclerView.adapter = adapter

    }

    override fun renderDevices(devices: List<MBluetoothDevice>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReceiveDevice() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
