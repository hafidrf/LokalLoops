package com.hafidrf.lokaloops.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.ListItemKeranjang
import kotlinx.android.synthetic.main.activity_transaksi.view.*
import kotlinx.android.synthetic.main.item_list_checkout.view.*


class ListCheckoutVH(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data: ListItemKeranjang, callback: ListCheckOutListener) {
        val keranjangSession: KeranjangSession = KeranjangSession(itemView.context)

        var harga = Integer.parseInt(data.item.price.toString())  * data.total
        itemView.tv_item_detail?.text = data.item.name
        itemView.tv_jumlah?.text = "  x "+data.total
//        itemView.tv_total_harga_barang?.text = " Rp "+tot
        itemView.tv_total_harga_barang?.text = " Rp "+harga
        itemView.tv_note?.text = data.catatan



        itemView.btn_delete?.setOnClickListener {
            callback.onDelete(data, adapterPosition)
            keranjangSession.remProduct(data.item,data.total,data.catatan)
        }

    }

//    interface Callback{
//        fun onSubmit(data: ListCheckoutVH, number:Int)
//    }

}

interface ListCheckOutListener{
    fun onDelete(data: ListItemKeranjang, position: Int)
}