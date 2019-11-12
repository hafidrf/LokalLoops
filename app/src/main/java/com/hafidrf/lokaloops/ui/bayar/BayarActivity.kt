package com.hafidrf.lokaloops.ui.bayar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import retrofit2.Callback
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.utils.Print_factur
import com.hafidrf.lokaloops.network.EndPoint
import com.hafidrf.lokaloops.network.InterfacePoint
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.PrefsUtil
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_bayar.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Response
import java.util.*
import java.text.NumberFormat


class BayarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar)

        // Initializing a String Array
        val metBayar = arrayOf("Tunai", "KartuKredit", "Ovo", "Sakuku")

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            metBayar // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        spin_bayar.adapter = adapter

        // Set an on item selected listener for spinner object
        spin_bayar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Display the selected item text on text view
                if (parent.getItemAtPosition(position).toString() == "KartuKredit") {
                    btn_tunai1.setVisibility(View.GONE)
                    btn_tunai2.setVisibility(View.GONE)
                    btn_tunai3.setVisibility(View.GONE)
                    btn_tunai4.setVisibility(View.GONE)
                    btn_tunai5.setVisibility(View.GONE)
                } else {
                    btn_tunai1.setVisibility(View.VISIBLE)
                    btn_tunai2.setVisibility(View.VISIBLE)
                    btn_tunai3.setVisibility(View.VISIBLE)
                    btn_tunai4.setVisibility(View.VISIBLE)
                    btn_tunai5.setVisibility(View.VISIBLE)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }


        val sharedPreference: SharedPreference = SharedPreference(this)
        val keranjangSession: KeranjangSession = KeranjangSession(this)
        val prefs by lazy { PrefsUtil(this) }



        val price = sharedPreference.getValueString("total_hrg")!!

        val tot = price.toString()

        //format rupiah
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        val hargaAwal = price.toDouble()
        val ppn = hargaAwal * 0.1
        val hargaKenaPpn = hargaAwal + ppn

        tv_jml_bayar?.text = formatRupiah.format(hargaKenaPpn)

        var ung_byr = ""
        var kembalian = 0.0

//        val listProduk = keranjangSession.getKeranjangFull()!!
//        
//        listProduk.forEach {
//            println(it.item.name)
//            coba += it.total * it.item.price!!
//        }
//        println("-->" + coba)


        var hargaAkhir = 0.00
        btn_diskon.setOnClickListener {
            val diskon = et_diskon.text.toString().toDouble() / 100
            val diskon2 = hargaAwal * diskon
            hargaAkhir = hargaKenaPpn - diskon2
//            tv_jml_bayar?.text = ":  Rp " + hargaAkhir
            tv_jml_bayar?.text = formatRupiah.format(hargaAkhir)
        }

        if (hargaAkhir.equals(0.00)){
            hargaAkhir = hargaKenaPpn
        }

        sharedPreference.save("hargaAkhir", hargaAkhir.toString())

        btn_manual?.setOnClickListener {
            btn20.isEnabled = true
            btn50.isEnabled = true
            btn100.isEnabled = true
            val total_bayar = et_byr_manual.text.toString()
            var n = Integer.parseInt(total_bayar)
            kembalian = n.toDouble() - hargaAkhir
//            tv_kembalian?.text = ":  Rp " + kembalian.toString()
            tv_kembalian?.text = ":  " + formatRupiah.format(kembalian)
            ung_byr = total_bayar
            var ungbyr = Integer.parseInt(ung_byr)
            sharedPreference.save("uang_bayar", ung_byr)
            tv_grand_bayar.text = ":  "+formatRupiah.format(ungbyr)
            et_byr_manual.text.clear()
        }
        btn20?.setOnClickListener {
            kembalian = 20000.00 - hargaAkhir
            ung_byr = "20000"
            sharedPreference.save("uang_bayar", ung_byr)
            tv_grand_bayar.text = ":  Rp 20.000"
            tv_kembalian?.text = ":  " + formatRupiah.format(kembalian)
//            tv_kembalian?.text = formatRupiah.format(kembalian)
            btn20.isEnabled = false
            btn50.isEnabled = true
            btn100.isEnabled = true
        }
        btn50?.setOnClickListener {
            kembalian = 50000.00 - hargaAkhir
            ung_byr = "50000"
            sharedPreference.save("uang_bayar", ung_byr)
            tv_grand_bayar.text = ":  Rp 50.000"
            tv_kembalian?.text = ":  " + formatRupiah.format(kembalian)
            tv_kembalian?.text = formatRupiah.format(kembalian)
            btn50.isEnabled = false
            btn20.isEnabled = true
            btn100.isEnabled = true
        }
        btn100?.setOnClickListener {
            kembalian = 100000.00 - hargaAkhir
            ung_byr = "100000"
            sharedPreference.save("uang_bayar", ung_byr)
            tv_grand_bayar.text = ":  Rp 100.000"
            tv_kembalian?.text = ":  " + formatRupiah.format(kembalian)
            tv_kembalian?.text = formatRupiah.format(kembalian)
            btn100.isEnabled = false
            btn20.isEnabled = true
            btn50.isEnabled = true
        }
        btn_uang_pas?.setOnClickListener {
            btn20.isEnabled = true
            btn50.isEnabled = true
            btn100.isEnabled = true
            var kem = "0"
            tv_kembalian?.text = ":  Rp " + kem
            kembalian = kem.toDouble()
            ung_byr = hargaAkhir.toString()
            sharedPreference.save("uang_bayar", ung_byr)
            tv_grand_bayar.text = ":  " + formatRupiah.format(hargaAkhir)
            tv_grand_bayar.text = formatRupiah.format(hargaAkhir)
            et_byr_manual.text.clear()
        }

        btn_sign_up?.setOnClickListener {
            tv_nama_pelanggan?.text = ":  " + et_nama_user?.text.toString()
            sharedPreference.save("nama_pembeli", et_nama_user?.text.toString())
        }

//        var nm_pm = prefs.getValueString("nama")
//        var idf = nm_pm + rnds.toString()

        var rnds = sharedPreference.getValueString("id_pembeli")
        val id_own = prefs.getValueString("id_owner")



        btn_print?.setOnClickListener {
            keranjangSession.addProductpembeli(rnds.toString(),et_nama_user?.text.toString(),hargaAkhir.toString(),
                ung_byr!!,kembalian.toString()!!,id_own.toString())

            requestPesanan()
            requestBayar()
            print()

        }

        btn_backk?.setOnClickListener {
            kembali()
        }


    }

    private fun requestBayar() {

        var data = KeranjangSession(this).getPembeliserver()

        EndPoint.client.create(InterfacePoint::class.java).saveData(data).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                toast("error ${t.message}")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                toast("berhasil")
            }

        })
    }

    private fun requestPesanan() {

        var data = KeranjangSession(this).getKeranjangServer()

        EndPoint.client.create(InterfacePoint::class.java).saveData2(data).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                toast("error ${t.message}")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                toast("berhasil")
            }

        })
    }

    fun kembali() {
        finish()
    }

    fun print() {
        val intent = Intent(this, Print_factur::class.java)
        startActivity(intent)
    }


}
