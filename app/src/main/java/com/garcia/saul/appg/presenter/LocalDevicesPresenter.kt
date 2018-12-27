package com.garcia.saul.appg.presenter

import com.garcia.saul.appg.data.model.MBluetoothDevice

class LocalDevicesPresenter : Presenter<LocalDevicesPresenter.View>() {


    interface View : Presenter.View {

        fun renderDevices(devices: List<MBluetoothDevice>)

        fun onReceiveDevice()

    }

}