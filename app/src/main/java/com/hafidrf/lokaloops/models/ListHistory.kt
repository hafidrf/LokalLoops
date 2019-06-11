package com.hafidrf.lokaloops.models

data class ListHistory(
    val id: String,
    val nama_pembeli: String,
    val total_bayar: String,
    val uang_bayar: String,
    val uang_kembali: String,
    val tanggal: String,
    val pesanan: ArrayList<com.hafidrf.lokaloops.models.Pesanan>
)

data class ListHistoryResponse(
    val result: ArrayList<com.hafidrf.lokaloops.models.ListHistory>
)

data class Pesanan(
    val id_pesan: String,
    val item: String,
    val harga: String,
    val jumlah_pesan: String,
    val total_harga: String
)