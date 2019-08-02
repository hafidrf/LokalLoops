package com.hafidrf.lokaloops.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.ListItemKeranjang
import kotlinx.android.synthetic.main.activity_transaksi.view.*
import kotlinx.android.synthetic.main.item_list_checkout.view.*
import java.text.NumberFormat
import java.util.*


class ListCheckoutVH(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data: ListItemKeranjang, callback: ListCheckOutListener, position: Int) {
        val keranjangSession: KeranjangSession = KeranjangSession(itemView.context)

        //format rupiah
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)


        var harga = Integer.parseInt(data.item.price.toString())  * data.total
        itemView.tv_item_detail?.text = data.item.name
        itemView.tv_jumlah?.text = "  x "+data.total
//        itemView.tv_total_harga_barang?.text = " Rp "+tot
        itemView.tv_total_harga_barang?.text = formatRupiah.format(harga)
        itemView.tv_note?.text = data.catatan



        itemView.btn_delete?.setOnClickListener {
            callback.onDelete(data, position)
//            keranjangSession.remProduct(data.item,data.total,data.catatan)
            println(position)
//            keranjangSession.remProduct(position)

        }

    }

//    interface Callback{
//        fun onSubmit(data: ListCheckoutVH, number:Int)
//    }

}

interface ListCheckOutListener{
    fun onDelete(data: ListItemKeranjang, position: Int)
}