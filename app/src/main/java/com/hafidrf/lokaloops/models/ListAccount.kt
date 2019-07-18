package com.hafidrf.lokaloops.models

data class ListAccount(
    val id: String,
    val nama_pembeli: String,
    val total_bayar: String,
    val uang_bayar: String,
    val uang_kembali: String,
    val tanggal: String,
    val pesanan: ArrayList<Pesanan>
)

data class ListAccountResponse(
    val result: ArrayList<ListHistory>
)