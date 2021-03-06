package com.hafidrf.lokaloops.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.common.VerticalSpaceItem
import com.hafidrf.lokaloops.models.Adapter
import com.hafidrf.lokaloops.models.ListItem
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.ListItemKeranjang
import com.hafidrf.lokaloops.utils.SharedPreference
import com.hafidrf.lokaloops.viewholder.ListCheckOutListener
import com.hafidrf.lokaloops.viewholder.ListCheckoutVH
import kotlinx.android.synthetic.main.activity_transaksi.*
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.util.*


class TransaksiActivity : AppCompatActivity(), ListCheckOutListener {

    companion object {

        fun getIntent(ctx: Context?) = Intent(ctx, TransaksiActivity::class.java)

    }

    fun bayarAkhir() {
        val intent = Intent(this, BayarActivity::class.java)
        startActivity(intent)
    }

    fun kembali() {
        finish()
    }

    private val session by lazy { KeranjangSession(this) }
    private lateinit var listAdapter: Adapter<ListItemKeranjang, ListCheckoutVH>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)


        val sharedPreference: SharedPreference = SharedPreference(this)
        val keranjangSession: KeranjangSession = KeranjangSession(this)


        var listProduk = keranjangSession.getKeranjangFull()!!

        for ((index, value) in listProduk.withIndex()) {
            println("the element at $index is $value")
        }


        //format rupiah
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)



        var coba = 0
        listProduk.forEach {
            println(it.item.name)
            coba += it.total!! * it.item.price!!
        }
        println("-->" + coba)

        sharedPreference.save("total_hrg", coba.toString())
        tv_hrg.text = formatRupiah.format(coba)

        listAdapter = object : Adapter<ListItemKeranjang, ListCheckoutVH>(
            R.layout.item_list_checkout,
            arrayListOf(),
            ListCheckoutVH::class.java,
            ListItemKeranjang::class.java
        ) {
            override fun bindView(holder: ListCheckoutVH, model: ListItemKeranjang, position: Int) {
                holder.bind(model, this@TransaksiActivity,position)
            }

        }

        rv_list_checkout?.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpaceItem(context, 5f, 2f))
        }

        listAdapter.updateList(listProduk)
        Log.e("data", listProduk.toString())

        btn_back.setOnClickListener {
            kembali()
        }
        btn_bayar_akhir.setOnClickListener {
            bayarAkhir()
        }

        btn_cancel_order?.setOnClickListener {
            listAdapter.clear()
            session.clearSharedPreference()
            kembali()
        }
    }

    override fun onDelete(data: ListItemKeranjang, position: Int) {
        listAdapter.removeItem(position)
        session.remProduct(position)
        finish()
        startActivity(intent)
    }




}
