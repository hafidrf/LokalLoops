package com.hafidrf.lokaloops.viewholder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.hafidrf.lokaloops.activities.DetailHistoryActivity
import com.hafidrf.lokaloops.models.ListHistory
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.history_list.view.*
import java.text.NumberFormat
import java.util.*


class ListitemRiwayat(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(data: ListHistory, callback: Callback) {
        val sharedPreference: SharedPreference = SharedPreference(itemView.context)
        val keranjangSession: KeranjangSession = KeranjangSession(itemView.context)


        //format rupiah
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)


        itemView.rv_to_hisdetail.setOnClickListener {
            callback.onClick(data)
        }

        itemView.tv_tanggal?.text = data.tanggal
        itemView.tv_no_order?.text = "LOK" + data.id + "LO" + 2019
        itemView.tv_costumer?.text = data.nama_pembeli
//        itemView.tv_total_harga?.text = data.total_bayar
        val uangBayar = data.uang_bayar.toDouble()
        itemView.tv_total_bayar?.text = formatRupiah.format(uangBayar)
        var ugbyr = formatRupiah.format(uangBayar)
//        itemView.tv_uang_kembali?.text = data.uang_kembali

        sharedPreference.save("uangBayar", ugbyr)
        sharedPreference.save("nama_pembeli", data.nama_pembeli)
        sharedPreference.save("tanggal", data.tanggal)
        sharedPreference.save("id_pembeli_riwayat", data.id)

        Log.e("data", data.toString())
        println(data.pesanan)

//        keranjangSession.addHis(data.id,data.nama_pembeli,data.total_bayar,data.uang_bayar,data.uang_kembali,data.tanggal,data.item)



//        itemView.rv_list_history_item?.apply {
//
//            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(com.hafidrf.lokaloops.common.VerticalSpaceItem(context, 5f, 2f))
//            adapter = object : com.hafidrf.lokaloops.models.Adapter<com.hafidrf.lokaloops.models.Pesanan, ListitemRiwayatDetail>(
//            R.layout.history_list_item,
//            arrayListOf(),
//            ListitemRiwayatDetail::class.java,
//            com.hafidrf.lokaloops.models.Pesanan::class.java
//        ){
//            override fun bindView(holder: ListitemRiwayatDetail, model: com.hafidrf.lokaloops.models.Pesanan, position: Int) {
//                holder.bind(model)
//            }
//
//        }
//        }


    }

    interface Callback {
        fun onSubmit(data: ListHistory, number: Int)
        fun onClick(data: ListHistory)
    }


}