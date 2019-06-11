package com.hafidrf.lokaloops.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_checkout.view.*
import kotlinx.android.synthetic.main.fragment_popup_order.*
import kotlinx.android.synthetic.main.item_list_checkout.view.*


class ListCheckoutVH(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data: ListCheckoutVH) {
//        val sharedPreference: SharedPreference = SharedPreference(itemView.context)
//
//        if (sharedPreference.getValueString("produk")!=null) {
//            val produk = sharedPreference.getValueString("produk")!!
//            val price = sharedPreference.getValueString("price")!!
//            val num = sharedPreference.getValueString("num")!!
//            var number = Integer.parseInt(num.toString())
//            var hrg = Integer.parseInt(price.toString())
//            var hasil = number*hrg
//            itemView.tv_item_detail?.text = produk
//            itemView.tv_harga?.text = " Rp "+price
//            itemView.tv_jumlah?.text = num
//            itemView.tv_total_harga_barang?.text = hasil.toString()
//        }else{
//            itemView.tv_name_check?.text = "nama : error"
//        }

//        itemView.tv_tanggal?.text = data.item
    }

//    interface Callback{
//        fun onSubmit(data: ListCheckoutVH, number:Int)
//    }


}