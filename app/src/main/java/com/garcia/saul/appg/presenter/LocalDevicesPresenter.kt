package com.garcia.saul.appg.presenter

import com.garcia.saul.appg.data.model.MBluetoothDevice
import com.garcia.saul.appg.interactor.LocalDevicesInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class LocalDevicesPresenter(localDevicesInteractor: LocalDevicesInteractor) : Presenter<LocalDevicesPresenter.View>() {

    private var interactor: LocalDevicesInteractor = localDevicesInteractor

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onPutDevice(device: MBluetoothDevice){

       val disposable: Disposable  = interactor.putDevice(device)
            .subscribe({getSuccess(it)},{getError(it)})

        compositeDisposable.add(disposable)
    }

    fun getSuccess(it: MBluetoothDevice) {
        view!!.onPutDeviceSuccess(it)
        compositeDisposable.clear()

    }

    fun getError(it: Throwable) {
        view!!.onPutDeviceError(it)
    }

    interface View : Presenter.View {

        fun onPutDeviceSuccess(device: MBluetoothDevice)

        fun onPutDeviceError(error: Throwable)

    }

}