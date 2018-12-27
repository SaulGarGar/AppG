package com.garcia.saul.appg.presenter

import com.garcia.saul.appg.data.model.MBluetoothDevice
import com.garcia.saul.appg.interactor.LocalDevicesInteractor
import io.reactivex.disposables.Disposable

class LocalDevicesPresenter(localDevicesInteractor: LocalDevicesInteractor) : Presenter<LocalDevicesPresenter.View>() {

    private var interactor: LocalDevicesInteractor = localDevicesInteractor

    fun onPutDevice(device: MBluetoothDevice){

        val disposable: Disposable = interactor.putDevice(device)
            .subscribe({getSuccess(it)},{getError(it)})
    }

    fun getSuccess(it: MBluetoothDevice) {
        view!!.onPutDeviceSuccess()
    }

    fun getError(it: Throwable) {

    }

    interface View : Presenter.View {

        fun onPutDeviceSuccess()

    }

}