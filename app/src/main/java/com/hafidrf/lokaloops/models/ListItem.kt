package com.hafidrf.lokaloops.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListItem(
    @SerializedName("id") @Expose val id: String?,
    @SerializedName("name") @Expose val name: String?,
    @SerializedName("price") @Expose val price: Int?,
    @SerializedName("foto") @Expose val foto: String?,
    @SerializedName("barcode") @Expose val barcode: String?,
    @SerializedName("quantity") @Expose val quantity: String?
){
    companion object {
        const val TB_ITEM: String = "TB_ITEM"
        const val ID: String = "id"
        const val NAME: String = "name"
        const val PRICE: String = "price"
        const val FOTO: String = "foto"
        const val BARCODE: String = "barcode"
        const val QUANTITY: String = "quantity"
    }
}

data class ListItemResponse(
    val result: ArrayList<ListItem>
)


