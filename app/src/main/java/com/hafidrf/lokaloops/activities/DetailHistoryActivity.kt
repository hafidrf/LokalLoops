package com.hafidrf.lokaloops.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.models.ListHistory
import com.hafidrf.lokaloops.utils.PrefsUtil
import com.hafidrf.lokaloops.viewholder.ListitemRiwayat
import kotlinx.android.synthetic.main.activity_detail_history.*

class DetailHistoryActivity : AppCompatActivity(){

    var array = arrayOf("Melbourne", "Vienna", "Vancouver", "Toronto", "Calgary", "Adelaide", "Perth", "Auckland", "Helsinki", "Hamburg", "Munich", "New York", "Sydney", "Paris", "Cape Town", "Barcelona", "London", "Bangkok")

    private val prefs by lazy { PrefsUtil(this) }

//    override fun onSubmit(data: ListHistory, number: Int) {
//        data.pesanan.forEach {
//            tv_produkitem.text = it.item
//            tv_jumlahpesan.text = it.jumlah_pesan
//            tv_hargabarang.text = it.total_harga
//        }
//        tv_no_order.text = data.id
//        tv_costumer.text = data.nama_pembeli
//        println(data.nama_pembeli)
//        Log.e("ini", data.toString())
//    }
//
//    override fun onClick(data: ListHistory) {
//        data.pesanan.forEach {
//            tv_produkitem.text = it.item
//            tv_jumlahpesan.text = it.jumlah_pesan
//            tv_hargabarang.text = it.total_harga
//
//        }
//        tv_no_order.text = data.id
//        tv_costumer.text = data.nama_pembeli
//        println(data.nama_pembeli)
//        Log.e("ini", data.toString())
//    }

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, DetailHistoryActivity::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)

//        val adapter = ArrayAdapter(this,
//            R.layout.ls_, array)
//
//        val listView:ListView = findViewById(R.id.listview_1)
//        listView.setAdapter(adapter)

        

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

        btn_back.setOnClickListener {
            finish()
        }


    }
}
