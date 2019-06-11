package com.hafidrf.lokaloops.models

data class ListCheckout(
    val produk: String,
    val price: String,
    val num: String
)

data class ListCheckoutResponse(
    val result: ArrayList<com.hafidrf.lokaloops.models.ListCheckout>
)
