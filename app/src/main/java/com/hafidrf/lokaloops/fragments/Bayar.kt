package com.hafidrf.lokaloops.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.models.ApiResponse
import com.hafidrf.lokaloops.models.ListItemResponse
import com.hafidrf.lokaloops.models.Test
import com.hafidrf.lokaloops.rest.EndPoint
import com.hafidrf.lokaloops.rest.InterfacePoint
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_bayar.*
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_popup_order.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.R.attr.password
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hafidrf.lokaloops.utils.Food
import com.leerybit.escpos.DeviceCallbacks
import com.leerybit.escpos.PosPrinter60mm
import com.leerybit.escpos.widgets.TicketPreview
import android.support.v7.app.AppCompatActivity
import android.support.v4.content.ContextCompat
import android.text.format.DateFormat
import com.hafidrf.lokaloops.activities.MainActivity
import com.hafidrf.lokaloops.activities.Print_factur
import com.leerybit.escpos.Ticket
import com.leerybit.escpos.TicketBuilder
import java.io.IOException
import java.util.*


class Bayar : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.hafidrf.lokaloops.R.layout.fragment_bayar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sharedPreference: SharedPreference = SharedPreference(view.context)
        val keranjangSession: KeranjangSession = KeranjangSession(view.context)

        val price = sharedPreference.getValueString("total_hrg")!!
//        val num = sharedPreference.getValueString("num")!!
//        var number = Integer.parseInt(num.toString())
//        var hrg = Integer.parseInt(price.toString())
//        var hasil = number * hrg
        val tot = price.toString()
        tv_jml_bayar?.text = ":  Rp "+price
        var ung_byr = ""
        var ung_kmbl = ""
        println("-->"+price)

        btn_manual?.setOnClickListener {
            btn20.isEnabled = true
            btn50.isEnabled = true
            btn100.isEnabled = true
            val total_bayar = et_byr_manual.text.toString()
            var n = Integer.parseInt(total_bayar)
//            var kembali = n-hasil
//            tv_kembalian?.text = ":  Rp "+kembali.toString()
//            ung_kmbl = kembali.toString()
//            ung_byr = total_bayar
            sharedPreference.save("uang_bayar",ung_byr)
            tv_grand_bayar.text = ":  Rp "+ung_byr
            et_byr_manual.text.clear()
        }
        btn20?.setOnClickListener {
//            var kembali = 20000 - hasil
//            ung_byr = "20000"
            sharedPreference.save("uang_bayar",ung_byr)
            tv_grand_bayar.text = ":  Rp 20000"
//            tv_kembalian?.text = ":  Rp " + kembali.toString()
//            ung_kmbl = kembali.toString()
            btn20.isEnabled = false
            btn50.isEnabled = true
            btn100.isEnabled = true
        }
        btn50?.setOnClickListener {
//            var kembali = 50000 - hasil
//            ung_byr = "50000"
            sharedPreference.save("uang_bayar",ung_byr)
            tv_grand_bayar.text = ":  Rp 50000"
//            tv_kembalian?.text = ":  Rp " + kembali.toString()
//            ung_kmbl = kembali.toString()
            btn50.isEnabled = false
            btn20.isEnabled = true
            btn100.isEnabled = true
        }
        btn100?.setOnClickListener {
//            var kembali = 100000 - hasil
//            ung_byr = "100000"
            sharedPreference.save("uang_bayar",ung_byr)
            tv_grand_bayar.text = ":  Rp 100000"
//            tv_kembalian?.text = ":  Rp " + kembali.toString()
//            ung_kmbl = kembali.toString()
            btn100.isEnabled = false
            btn20.isEnabled = true
            btn50.isEnabled = true
        }


        btn_print?.setOnClickListener {

            print()

//            //----start json-------
//            var listProduk = keranjangSession.getKeranjangFull()!!
//            Log.e("check" , listProduk.toString())
//
////                val food = Food()
//            for (i in listProduk.indices) {
//                Log.e("check" , listProduk[i].toString())
////                food.id = listProduk[]
////                food.total = listProduk[]
////                food.catatan = "password"
//            }
////                EndPoint.client.create(InterfacePoint::class.java).saveData(ap.nama_pembeli,ap.total_bayar,ap.uang_bayar,ap.uang_kembali,ap.item,ap.harga,ap.jumlah_pesan,ap.total_harga).enqueue(object : Callback<ApiResponse> {
////                    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
////                        if (response.isSuccessful) {
////                            his()
////                        }
////                    }
////
////                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
////
////                    }
////                })
//            //-----end of json-----
//
//
//            //-------
//
//            val produk = sharedPreference.getValueString("produk")!!
//            val price = sharedPreference.getValueString("price")!!
//            val num = sharedPreference.getValueString("num")!!
//            val nama_pembeli = sharedPreference.getValueString("nama_pembeli")!!
//
//            val ap = Test()
//
//            //akses setter untuk mengatur nilainya
//            ap.nama_pembeli = nama_pembeli
//            ap.total_bayar = tot
//            ap.uang_bayar = ung_byr
//            ap.uang_kembali = ung_kmbl
//            ap.item = produk
//            ap.harga = price
//            ap.jumlah_pesan = num
//            ap.total_harga = tot
//
//            //--------
//
////                EndPoint.client.create(InterfacePoint::class.java).saveData(ap.nama_pembeli,ap.total_bayar,ap.uang_bayar,ap.uang_kembali,ap.item,ap.harga,ap.jumlah_pesan,ap.total_harga).enqueue(object : Callback<ApiResponse> {
////                    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
////                        if (response.isSuccessful) {
////                            his()
////                        }
////                    }
////
////                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
////
////                    }
////                })
//
//            sharedPreference.clearSharedPreference()


        }

        btn_sign_up?.setOnClickListener{
            tv_nama_pelanggan?.text =  ":  "+et_nama_user?.text.toString()
            sharedPreference.save("nama_pembeli",et_nama_user?.text.toString())
        }

        btn_backk?.setOnClickListener {
            kembali()
        }
    }

    private fun kembali() {
        val fragmentCheckout = com.hafidrf.lokaloops.fragments.Checkout()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragmentCheckout)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun print() {
        val intent = Intent (getActivity(), Print_factur::class.java)
        getActivity()?.startActivity(intent)
    }

    private fun his() {
        val fragmentHis = HistoryFragment()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragmentHis)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
