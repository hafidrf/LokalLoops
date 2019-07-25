package com.hafidrf.lokaloops.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EndPoint {
    val BASE_URL = "https://hafidrf.com/lokaloops/"
    private var retrofit: Retrofit? = null
    val client: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
}