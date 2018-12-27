package com.garcia.saul.appg.interactor

import com.garcia.saul.appg.data.api.client.GrinService
import com.garcia.saul.appg.data.model.MBluetoothDevice
import io.reactivex.Observable

class LocalDevicesInteractor(grinSer: GrinService) {

    private var grinService: GrinService = grinSer

    fun putDevice(device: MBluetoothDevice): Observable<MBluetoothDevice> {

        return grinService.putDevice(device)

    }

}