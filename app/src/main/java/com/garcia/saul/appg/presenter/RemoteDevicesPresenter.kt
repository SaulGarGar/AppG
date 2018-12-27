package com.garcia.saul.appg.presenter

import com.garcia.saul.appg.data.model.MBluetoothDevice
import com.garcia.saul.appg.interactor.RemoteDevicesInteractor
import io.reactivex.disposables.Disposable

class RemoteDevicesPresenter(remoteDevicesInteractor: RemoteDevicesInteractor) : Presenter<RemoteDevicesPresenter.View>() {

    private var interactor: RemoteDevicesInteractor = remoteDevicesInteractor

    fun RemoteDevicesPresenter(interactor: RemoteDevicesInteractor){
        this.interactor = interactor
    }

    fun onSearchDevices(){

        var disposable: Disposable = interactor.searchDevices().subscribe {

            val devices: ArrayList<MBluetoothDevice> = ArrayList()
            if (it.isNotEmpty()){
                devices.addAll(it)
                view!!.renderDevices(devices)
            }

        }

    }

    interface View : Presenter.View {

        fun onClickReorder()

        fun renderDevices(devices: List<MBluetoothDevice>)
    }
}