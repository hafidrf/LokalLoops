package com.hafidrf.lokaloops.models

class Test {
    var nama_pembeli : String = ""
    var total_bayar : String = ""
    var uang_bayar : String = ""
    var uang_kembali : String = ""
    var item : String = ""
    var harga : String = ""
    var jumlah_pesan : String = ""
    var total_harga : String = ""

    //getter
    get() = field

    //setter
    set(value) {
        field = value
    }
}

data class ApiResponse(
    val result: ArrayList<Test>
)