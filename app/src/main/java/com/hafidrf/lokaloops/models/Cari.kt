package com.hafidrf.lokaloops.models

class Cari {
    var name : String = ""

        //getter
        get() = field

        //setter
        set(value) {
            field = value
        }
}

data class CariResponse(
    val result: ArrayList<Cari>
)