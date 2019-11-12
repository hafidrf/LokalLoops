package com.hafidrf.lokaloops.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.models.*
import com.hafidrf.lokaloops.rest.EndPoint
import com.hafidrf.lokaloops.rest.InterfacePoint
import com.hafidrf.lokaloops.utils.HystorySession
import com.hafidrf.lokaloops.utils.PrefsUtil
import com.hafidrf.lokaloops.utils.SharedPreference
import com.hafidrf.lokaloops.viewholder.ListitemRiwayat
import kotlinx.android.synthetic.main.activity_detail_history.*
import kotlinx.android.synthetic.main.dialog_edit_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailHistoryActivity : AppCompatActivity(), ListitemRiwayat.Callback{
    override fun onSubmit(data: ListHistory, number: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(data: ListHistory, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dataSend(data: ListHistory, position: Int) {
            println("hahaha"+data.nama_pembeli)
    }

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



    private lateinit var dataa : Pesanann

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)
        val sharedPreference: SharedPreference = SharedPreference(this)
        val HystorySession: HystorySession = HystorySession(this)

        var id_pembeli_riwayat = sharedPreference.getValueString("id_pembeli_riwayat")
        val listProduk = HystorySession.getKeranjangFull()!!

        println(id_pembeli_riwayat+"inilah")

//        for (i in 0 until listProduk.size) {
////            if (listProduk[i].id == id_pembeli_his){
//                println(
//                    "ini perulangan " + listProduk[i].id + "^^" + id_pembeli_riwayat + "--" + listProduk[i].item.jumlah_pesan
//                            + listProduk[i].item.harga
//                )
//            tv_produkitem.text = listProduk[i].item.item
//            tv_jumlahpesan.text = listProduk[i].item.jumlah_pesan
//            tv_hargabarang.text = listProduk[i].item.harga
////            }
//        }

//        listProduk.forEach {
//            println("ini coba "+it.item.item+it.item.harga+it.item.total_harga)
//            tv_produkitem.text = it.item.item
//            tv_jumlahpesan.text = it.item.jumlah_pesan
//            tv_hargabarang.text = it.item.total_harga
//        }

        var uangBayar = sharedPreference.getValueString("uangBayar")
        var nama_pembeli = sharedPreference.getValueString("nama_pembeli")
        var tanggal = sharedPreference.getValueString("tanggal")


//        EndPoint.client.create(InterfacePoint::class.java).listHistoryDetail(id_pembeli_riwayat.toString()).enqueue(object: Callback<Pesanann>{
//            override fun onResponse(call: Call<Pesanann>, response: Response<Pesanann>) {
//                if (response.isSuccessful) {
//                    dataa = response.body()!!
//                    this@DetailHistoryActivity.tv_total_harga?.text = dataa.jumlah_pesan
//                } else {
//                }
//            }
//            override fun onFailure(call: Call<Pesanann>, t: Throwable) {
//                Toast.makeText(this@DetailHistoryActivity, "Gagal mengganti password", Toast.LENGTH_LONG).show()
//            }
//
//        })



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
            HystorySession.clearSharedPreference()
        }


    }
}
