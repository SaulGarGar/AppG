package com.garcia.saul.appg.data.model

import com.google.gson.annotations.SerializedName

class MBluetoothDevice(
    @SerializedName("_id") var id: String? = "",
    @SerializedName("name")var name: String? = "",
    @SerializedName("address")var address: String? = "",
    @SerializedName("strength")var strength: String? = "",
    @SerializedName("created_at")var createdAt: String? = ""
)