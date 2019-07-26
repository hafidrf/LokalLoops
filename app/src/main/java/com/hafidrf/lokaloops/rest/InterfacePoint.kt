package com.hafidrf.lokaloops.rest

import com.hafidrf.lokaloops.models.*
import retrofit2.Call
import com.hafidrf.lokaloops.models.ApiResponse
import com.hafidrf.lokaloops.utils.Food
import com.hafidrf.lokaloops.utils.ListItemKeranjang
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

    @GET("edit_password")
    fun listAccount(): Call<ListAccountResponse>

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

    @POST("kirim")
    fun saveData(@Body food: String): Call<String>


}