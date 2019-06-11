package com.hafidrf.lokaloops.viewholder

import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.common.VerticalSpaceItem
import com.hafidrf.lokaloops.models.Adapter
import com.hafidrf.lokaloops.models.ListHistory
import com.hafidrf.lokaloops.models.Pesanan
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.history_list.view.*


class ListitemRiwayat(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data: com.hafidrf.lokaloops.models.ListHistory, callback: Callback) {
        val sharedPreference: SharedPreference = SharedPreference(itemView.context)

        itemView.tv_tanggal?.text = data.tanggal
        itemView.tv_no_order?.text = data.id
        itemView.tv_costumer?.text = data.nama_pembeli
        itemView.tv_total_harga?.text = data.total_bayar
        itemView.tv_total_bayar?.text = data.uang_bayar
        itemView.tv_uang_kembali?.text = data.uang_kembali
        Log.e("data", data.toString())

        itemView.rv_list_history_item?.apply {

            layoutManager = LinearLayoutManager(context)
            addItemDecoration(com.hafidrf.lokaloops.common.VerticalSpaceItem(context, 5f, 2f))
            adapter = object : com.hafidrf.lokaloops.models.Adapter<com.hafidrf.lokaloops.models.Pesanan, ListitemRiwayatDetail>(
            R.layout.history_list_item,
            arrayListOf(),
            ListitemRiwayatDetail::class.java,
            com.hafidrf.lokaloops.models.Pesanan::class.java
        ){
            override fun bindView(holder: ListitemRiwayatDetail, model: com.hafidrf.lokaloops.models.Pesanan, position: Int) {
                holder.bind(model)
            }

        }
        }


    }

    interface Callback{
        fun onSubmit(data: com.hafidrf.lokaloops.models.ListHistory, number:Int)
    }


}