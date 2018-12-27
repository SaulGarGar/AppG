package com.garcia.saul.appg.data.api.client

import com.garcia.saul.appg.BuildConfig
import com.garcia.saul.appg.data.model.MBluetoothDevice
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GrinService {

    @GET(BuildConfig.ENDPOINT_DEVICES)
    fun getAllDevices():
            Observable<List<MBluetoothDevice>>


    @POST(BuildConfig.ENDPOINT_ADD)
    fun putDevice(@Body device: MBluetoothDevice):
            Observable<MBluetoothDevice>
}