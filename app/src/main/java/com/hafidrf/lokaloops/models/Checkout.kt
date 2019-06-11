package com.hafidrf.lokaloops.models

//import kotlinx.android.parcel.Parcelize

class Checkout{
    var id:Int = 0
    var namaItem:String? = null
    var price:String? = null
    var quantity:String? = null

    constructor(){}

    constructor(id: Int, namaItem: String, price: String, quantity: String){
        this.id = id
        this.namaItem = namaItem
        this.price = price
        this.quantity = quantity
    }
}