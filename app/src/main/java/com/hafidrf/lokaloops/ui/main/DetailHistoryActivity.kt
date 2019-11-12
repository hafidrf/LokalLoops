package com.hafidrf.lokaloops.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.models.*
import com.hafidrf.lokaloops.network.EndPoint
import com.hafidrf.lokaloops.network.InterfacePoint
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_detail_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailHistoryActivity : AppCompatActivity() {
    
    private lateinit var dataa: Pesanann

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)
        val sharedPreference = SharedPreference(this)

        var id_pembeli_riwayat = sharedPreference.getValueString("id_pembeli_riwayat")

        EndPoint.client.create(InterfacePoint::class.java)
            .listHistoryDetail(id_pembeli_riwayat.toString()).enqueue(object : Callback<Pesanann> {
            override fun onResponse(call: Call<Pesanann>, response: Response<Pesanann>) {
                if (response.isSuccessful) {
                    dataa = response.body()!!
                    this@DetailHistoryActivity.tv_total_harga?.text = dataa.jumlah_pesan
                } else {
                }
            }

            override fun onFailure(call: Call<Pesanann>, t: Throwable) {
                Toast.makeText(
                    this@DetailHistoryActivity,
                    "Gagal mengganti password",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
        btn_back.setOnClickListener {
            finish()
        }


    }
}
