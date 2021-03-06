package com.garcia.saul.appg.presenter

import android.util.Log
import com.garcia.saul.appg.data.model.MBluetoothDevice
import com.garcia.saul.appg.interactor.RemoteDevicesInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


class RemoteDevicesPresenter(remoteDevicesInteractor: RemoteDevicesInteractor) : Presenter<RemoteDevicesPresenter.View>() {

    private var interactor: RemoteDevicesInteractor = remoteDevicesInteractor

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onSearchDevices() {

        val disposable: Disposable = interactor.searchDevices()
            .subscribe({getSuccess(it)},{getError(it)})

        compositeDisposable.add(disposable)
    }

    fun getSuccess(devices: List<MBluetoothDevice>){

        view!!.renderDevices(devices)

    }

    fun getError(throwable: Throwable) {
        Log.e("RemoteDevicesPresenter", throwable.toString())
    }


    interface View : Presenter.View {

        fun onClickReorder()

        fun renderDevices(devices: List<MBluetoothDevice>)

        fun showProgress()

        fun hideProgress()

    }
}