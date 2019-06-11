package com.hafidrf.lokaloops.models

data class ListItem(
    val id: String?,
    val name: String?,
    val price: String?,
    val foto: String?,
    val barcode: String?,
    val quantity: String?
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


