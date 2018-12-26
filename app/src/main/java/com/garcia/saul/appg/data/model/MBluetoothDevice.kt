package com.garcia.saul.appg.data.model

class MBluetoothDevice(
    var id: String? = "",
    var name: String? = "",
    var address: String? = "",
    var strength: String? = "",
    var createdAt: String? = ""
) {
    constructor(name: String, address: String?, strength: String) : this()

}