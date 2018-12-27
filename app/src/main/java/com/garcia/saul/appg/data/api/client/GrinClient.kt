package com.garcia.saul.appg.data.api.client

import com.garcia.saul.appg.data.api.retrofit.RetrofitClient
import com.garcia.saul.appg.data.model.MBluetoothDevice
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GrinClient : RetrofitClient(), GrinService{

    override fun getAllDevices(): Observable<List<MBluetoothDevice>> {
        return getGrinService().getAllDevices()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun putDevice(device: MBluetoothDevice): Observable<MBluetoothDevice> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}