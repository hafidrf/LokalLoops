package com.hafidrf.lokaloops.network

import com.hafidrf.lokaloops.models.*
import retrofit2.Call
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

    @GET("riwayatdetail")
    fun listHistoryDetail(
        @Query("id_pembeli_riwayat") id_pembeli_riwayat:String
        ): Call<Pesanann>

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