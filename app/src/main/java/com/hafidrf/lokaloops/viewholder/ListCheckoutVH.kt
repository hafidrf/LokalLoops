package com.hafidrf.lokaloops.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.ListItemKeranjang
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_checkout.view.*
import kotlinx.android.synthetic.main.fragment_popup_order.*
import kotlinx.android.synthetic.main.item_list_checkout.view.*


class ListCheckoutVH(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data: ListItemKeranjang) {
        val keranjangSession: KeranjangSession = KeranjangSession(itemView.context)

        var harga = Integer.parseInt(data.item.price.toString())  * data.total
        itemView.tv_item_detail?.text = data.item.name
        itemView.tv_jumlah?.text = "  x "+data.total
//        itemView.tv_total_harga_barang?.text = " Rp "+tot
        itemView.tv_total_harga_barang?.text = " Rp "+harga
        itemView.tv_note?.text = data.catatan
    }

//    interface Callback{
//        fun onSubmit(data: ListCheckoutVH, number:Int)
//    }


}