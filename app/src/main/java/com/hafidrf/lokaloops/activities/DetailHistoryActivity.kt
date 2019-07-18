package com.hafidrf.lokaloops.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.fragments.HistoryFragment
import com.hafidrf.lokaloops.models.ListHistory
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.viewholder.ListItemVH
import com.hafidrf.lokaloops.viewholder.ListitemRiwayat
import kotlinx.android.synthetic.main.activity_detail_history.*
import kotlinx.android.synthetic.main.activity_detail_history.tv_stock
import kotlinx.android.synthetic.main.activity_transaksi.*
import kotlinx.android.synthetic.main.item_list.*
import org.jetbrains.anko.support.v4.intentFor

class DetailHistoryActivity : AppCompatActivity(), ListitemRiwayat.Callback {

    override fun onSubmit(data: ListHistory, number: Int) {
        data.pesanan.forEach {
            tv_produkitem.text = it.item
            tv_jumlahpesan.text = it.jumlah_pesan
            tv_hargabarang.text = it.total_harga
        }
        tv_no_order.text = data.id
        tv_costumer.text = data.nama_pembeli
        println(data.nama_pembeli)
        Log.e("ini", data.toString())
    }

    override fun onClick(data: ListHistory) {
        data.pesanan.forEach {
            tv_produkitem.text = it.item
            tv_jumlahpesan.text = it.jumlah_pesan
            tv_hargabarang.text = it.total_harga

        }
        tv_no_order.text = data.id
        tv_costumer.text = data.nama_pembeli
        println(data.nama_pembeli)
        Log.e("ini", data.toString())
    }

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, DetailHistoryActivity::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)

        

//        val keranjangSession: KeranjangSession = KeranjangSession(this)
//
//        var listProduk = keranjangSession.getHistoryFull()!!
//
//        for ((index, value) in listProduk.withIndex()) {
//            println("the element at $index is $value")
//        }
//
//        var tgl = ""
//        var nopel = ""
//        listProduk.forEach {
//            tv_produkitem.text = it.item.item
//            tv_jumlahpesan.text = it.item.jumlah_pesan
//            tv_hargabarang.text = it.item.total_harga
//            tgl = it.tanggal
//            nopel = it.id
//        }
//        tv_tanggal.text = tgl
//        tv_no_order.text = nopel

    }
}
