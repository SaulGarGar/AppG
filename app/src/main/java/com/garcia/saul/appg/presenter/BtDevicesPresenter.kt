package com.garcia.saul.appg.presenter

import com.garcia.saul.appg.data.model.MBluetoothDevice

class BtDevicesPresenter : Presenter<BtDevicesPresenter.View>() {


    interface View : Presenter.View {

        fun renderDevices(devices: List<MBluetoothDevice>)

        fun onReceiveDevice()

    }

}