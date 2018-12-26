package com.garcia.saul.appg.data.model

class MBluetoothDevice(
    private var id: String? = "",
    private var name: String? = "",
    private var address: String? = "",
    private var strength: String? = "",
    private var createdAt: String? = ""
) {
    constructor(name: String, address: String?, strength: String) : this()

}