package com.hafidrf.lokaloops.activities

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_bayar.*
import org.jetbrains.anko.themedImageSwitcher

class BayarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar)

        // Initializing a String Array
        val metBayar = arrayOf("Tunai","KartuKredit")

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            metBayar // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        spin_bayar.adapter = adapter;

        // Set an on item selected listener for spinner object
        spin_bayar.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                if (parent.getItemAtPosition(position).toString() == "KartuKredit"){
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

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        val sharedPreference: SharedPreference = SharedPreference(this)
        val keranjangSession: KeranjangSession = KeranjangSession(this)

        val price = sharedPreference.getValueString("total_hrg")!!
        val tot = price.toString()
        tv_jml_bayar?.text = ":  Rp "+price
        var ung_byr = ""
        var ung_kmbl = ""

        var listProduk = keranjangSession.getKeranjangFull()!!
        var coba = 0
        listProduk.forEach {
            println(it.item.name)
            coba += it.total * it.item.price!!
        }
        println("-->"+coba)

        sharedPreference.save("total_hrg", coba.toString())

        btn_manual?.setOnClickListener {
            btn20.isEnabled = true
            btn50.isEnabled = true
            btn100.isEnabled = true
            val total_bayar = et_byr_manual.text.toString()
            var n = Integer.parseInt(total_bayar)
            var kembali = n-coba
            tv_kembalian?.text = ":  Rp "+kembali.toString()
            ung_kmbl = kembali.toString()
            ung_byr = total_bayar
            sharedPreference.save("uang_bayar",ung_byr)
            tv_grand_bayar.text = ":  Rp "+ung_byr
            et_byr_manual.text.clear()
        }
        btn20?.setOnClickListener {
            var kembali = 20000 - coba
            ung_byr = "20000"
            sharedPreference.save("uang_bayar",ung_byr)
            tv_grand_bayar.text = ":  Rp 20000"
            tv_kembalian?.text = ":  Rp " + kembali.toString()
            ung_kmbl = kembali.toString()
            btn20.isEnabled = false
            btn50.isEnabled = true
            btn100.isEnabled = true
        }
        btn50?.setOnClickListener {
            var kembali = 50000 - coba
            ung_byr = "50000"
            sharedPreference.save("uang_bayar",ung_byr)
            tv_grand_bayar.text = ":  Rp 50000"
            tv_kembalian?.text = ":  Rp " + kembali.toString()
            ung_kmbl = kembali.toString()
            btn50.isEnabled = false
            btn20.isEnabled = true
            btn100.isEnabled = true
        }
        btn100?.setOnClickListener {
            var kembali = 100000 - coba
            ung_byr = "100000"
            sharedPreference.save("uang_bayar",ung_byr)
            tv_grand_bayar.text = ":  Rp 100000"
            tv_kembalian?.text = ":  Rp " + kembali.toString()
            ung_kmbl = kembali.toString()
            btn100.isEnabled = false
            btn20.isEnabled = true
            btn50.isEnabled = true
        }
        btn_print?.setOnClickListener {
            print()
        }

        btn_sign_up?.setOnClickListener{
            tv_nama_pelanggan?.text =  ":  "+et_nama_user?.text.toString()
            sharedPreference.save("nama_pembeli",et_nama_user?.text.toString())
        }

        btn_backk?.setOnClickListener {
            kembali()
        }
    }

    fun kembali(){
        finish()
    }

    fun print(){
        val intent = Intent(this, Print_factur::class.java)
        startActivity(intent)
    }
}