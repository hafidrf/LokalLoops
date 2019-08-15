package com.hafidrf.lokaloops.rest

import com.hafidrf.lokaloops.models.*
import retrofit2.Call
import com.hafidrf.lokaloops.models.ApiResponse
import com.hafidrf.lokaloops.utils.ListItemKeranjang
import com.hafidrf.lokaloops.utils.ListPembeli
import retrofit2.http.*


interface InterfacePoint {

    @GET("testapi")
    fun login(
        @Query("username") username:String,
        @Query("password") password:String
    ): Call<com.hafidrf.lokaloops.models.UserResponse>



    @GET("store_offline")
    fun listItem(): Call<ListItemResponse>

    @GET("store_online")
    fun listItem2(): Call<ListItemResponse>

    @GET("riwayat")
    fun listHistory(): Call<ListHistoryResponse>

//    @POST("kirim")
//    fun saveData(@Body test: Test ): Call<ApiResponse>

//    @GET("kirim")
//    fun saveData(
//        @Query("nama_pembeli") nama_pembeli:String,
//        @Query("total_bayar") total_bayar:String,
//        @Query("uang_bayar") uang_bayar:String,
//        @Query("uang_kembali") uang_kembali:String,
//        @Query("item") item:String,
//        @Query("harga") harga:String,
//        @Query("jumlah_pesan") jumlah_pesan:String,
//        @Query("total_harga") total_harga:String
//    ): Call<ApiResponse>

    @GET("cari")
    fun cariData(
        @Query("name") name:String
    ): Call<ListItemResponse>

    @POST("kirim1")
    fun saveData(@Body listPembeli: String): Call<String>

    @POST("kirim2")
    fun saveData2(@Body ListPesanan: String): Call<String>

    @GET("stock")
    fun saveStock(
        @Query("id") id:String,
        @Query("stock") stock:String
    ): Call<stockResponse>

    @GET("stock2")
    fun saveStock2(
        @Query("id") id:String,
        @Query("stock") stock:String
    ): Call<stockResponse>

    @GET("edit_password")
    fun kirimPass(
        @Query("id") id:String,
        @Query("password_baru") password_baru: String
    ): Call<NewPassResponse>

}