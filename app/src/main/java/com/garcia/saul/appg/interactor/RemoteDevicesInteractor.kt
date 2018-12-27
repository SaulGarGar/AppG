package com.garcia.saul.appg.interactor

import com.garcia.saul.appg.data.api.client.GrinService
import com.garcia.saul.appg.data.model.MBluetoothDevice
import io.reactivex.Observable

class RemoteDevicesInteractor(grinSer: GrinService) {

    private var grinService: GrinService = grinSer

    fun RemoteDevicesInteractor(grinService: GrinService){
        this.grinService = grinService
    }

    fun searchDevices(): Observable<List<MBluetoothDevice>> {
        return grinService.getAllDevices()
    }

}