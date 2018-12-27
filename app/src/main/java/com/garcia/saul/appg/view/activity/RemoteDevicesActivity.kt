package com.garcia.saul.appg.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.garcia.saul.appg.R
import com.garcia.saul.appg.data.api.client.GrinClient
import com.garcia.saul.appg.data.model.MBluetoothDevice
import com.garcia.saul.appg.interactor.RemoteDevicesInteractor
import com.garcia.saul.appg.presenter.RemoteDevicesPresenter

class RemoteDevicesActivity : AppCompatActivity(), RemoteDevicesPresenter.View {


    private lateinit var presenter: RemoteDevicesPresenter
    private var grinClient: GrinClient = GrinClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_devices)

        presenter = RemoteDevicesPresenter(RemoteDevicesInteractor(grinClient))

        initPresenter()

    }

    fun initPresenter(){
        presenter.view = this

        presenter.onSearchDevices()
    }

    override fun onClickReorder() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun renderDevices(devices: List<MBluetoothDevice>) {
        for (device in devices){

        }
    }
}
