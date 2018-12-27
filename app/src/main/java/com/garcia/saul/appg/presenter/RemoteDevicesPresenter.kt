package com.garcia.saul.appg.presenter

import android.nfc.Tag
import android.util.Log
import com.garcia.saul.appg.data.model.MBluetoothDevice
import com.garcia.saul.appg.interactor.RemoteDevicesInteractor
import io.reactivex.disposables.Disposable


class RemoteDevicesPresenter(remoteDevicesInteractor: RemoteDevicesInteractor) : Presenter<RemoteDevicesPresenter.View>() {

    private var interactor: RemoteDevicesInteractor = remoteDevicesInteractor

    fun onSearchDevices() {

        val disposable: Disposable= interactor.searchDevices()
            .subscribe({getSuccess(it)},{getError(it)})


    }

    fun getSuccess(devices: List<MBluetoothDevice>){

        val devs: ArrayList<MBluetoothDevice> = ArrayList()

        devs.addAll(devices)

        view!!.renderDevices(devs)

    }

    fun getError(throwable: Throwable) {
        Log.e("RemoteDevicesPresenter", throwable.toString())
    }


    interface View : Presenter.View {

        fun onClickReorder()

        fun renderDevices(devices: ArrayList<MBluetoothDevice>)

        fun showProgress()

        fun hideProgress()

    }
}