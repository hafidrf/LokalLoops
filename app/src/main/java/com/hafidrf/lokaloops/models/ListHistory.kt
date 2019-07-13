package com.hafidrf.lokaloops.models

data class ListHistory(
    val id: String,
    val nama_pembeli: String,
    val total_bayar: String,
    val uang_bayar: String,
    val uang_kembali: String,
    val tanggal: String,
    val pesanan: ArrayList<Pesanan>
)

data class ListHistoryResponse(
    val result: ArrayList<ListHistory>
)

data class Pesanan(
    val id_pesan: String,
    val item: String,
    val harga: String,
    val jumlah_pesan: String,
    val total_harga: String
)

//package com.hafidrf.lokaloops.models
//
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//
//data class ListHistory(
//    @SerializedName("id") @Expose val id: String,
//    @SerializedName("nama_pembeli") @Expose val nama_pembeli: String,
//    @SerializedName("total_bayar") @Expose val total_bayar: String,
//    @SerializedName("uang_bayar") @Expose val uang_bayar: String,
//    @SerializedName("uang_kembali") @Expose val uang_kembali: String,
//    @SerializedName("tanggal") @Expose val tanggal: String,
//    val item: Pesanan
//)
//
//data class ListHistoryResponse(
//    val result: ArrayList<ListHistory>
//)
//
//data class Pesanan(
//    @SerializedName("id_pesan") @Expose val id_pesan: String,
//    @SerializedName("item") @Expose val item: String,
//    @SerializedName("harga") @Expose val harga: String,
//    @SerializedName("jumlah_pesan") @Expose val jumlah_pesan: String,
//    @SerializedName("total_harga") @Expose val total_harga: String
//)
