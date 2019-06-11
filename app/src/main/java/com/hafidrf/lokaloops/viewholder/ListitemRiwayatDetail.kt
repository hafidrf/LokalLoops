package com.hafidrf.lokaloops.viewholder

import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.models.ListHistory
import com.hafidrf.lokaloops.models.Pesanan
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.history_list.view.*
import kotlinx.android.synthetic.main.history_list_item.view.*


class ListitemRiwayatDetail(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data: com.hafidrf.lokaloops.models.Pesanan) {

        itemView.tv_id_pesan?.text = data.id_pesan
        itemView.tv_item_detail?.text = data.item
        itemView.tv_harga?.text = data.harga
        itemView.tv_jumlah?.text = data.jumlah_pesan
        itemView.tv_total_harga_barang?.text = data.total_harga

    }
//
//    interface Callback{
//        fun onSubmit(data: ListHistory, number:Int)
//    }


}