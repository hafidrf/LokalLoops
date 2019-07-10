package com.hafidrf.lokaloops.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.activities.MainActivity
import com.hafidrf.lokaloops.activities.TransaksiActivity
import com.hafidrf.lokaloops.common.VerticalSpaceItem
import com.hafidrf.lokaloops.models.Adapter
import com.hafidrf.lokaloops.models.ListItem
import com.hafidrf.lokaloops.models.ListItemResponse
import com.hafidrf.lokaloops.rest.EndPoint
import com.hafidrf.lokaloops.rest.InterfacePoint
import com.hafidrf.lokaloops.utils.SharedPreference
import com.hafidrf.lokaloops.viewholder.ListItemVH
import kotlinx.android.synthetic.main.fragment_checkout.*
import com.hafidrf.lokaloops.models.ListCheckout
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.ListItemKeranjang
import com.hafidrf.lokaloops.viewholder.ListCheckoutVH
import kotlinx.android.synthetic.main.fragment_checkout.view.*
import kotlinx.android.synthetic.main.fragment_store.*
import kotlinx.android.synthetic.main.item_list_checkout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Checkout : Fragment(){



    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, com.hafidrf.lokaloops.fragments.Checkout::class.java)

    }

    fun addCustomer(){
        val AddCustomer = com.hafidrf.lokaloops.fragments.AddCustomerToOrder()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.container, AddCustomer)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun kembali(){
//        val intent = Intent (activity, MainActivity::class.java)
//        activity!!.startActivity(intent)

    }
    fun bayarAkhir(){
        val bayarin = com.hafidrf.lokaloops.fragments.Bayar()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.container, bayarin)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private lateinit var listAdapter : Adapter<ListItemKeranjang, ListCheckoutVH>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference: SharedPreference = SharedPreference(view.context)
        val keranjangSession: KeranjangSession = KeranjangSession(view.context)

//        if (sharedPreference.getValueString("produk")!=null) {
//            val produk = sharedPreference.getValueString("produk")!!
//            val price = sharedPreference.getValueString("price")!!
//            val num = sharedPreference.getValueString("num")!!
//            val catatan = sharedPreference.getValueString("catatan")!!
//            var produk = keranjangSession.getKeranjangFull()!!
//            var number = Integer.parseInt(num.toString())
//            var hrg = Integer.parseInt(price.toString())
//            var hasil = number * hrg
//            val tot = hasil.toString()

//            sharedPreference.save("tot",tot)
//            tv_item_detail?.text = produk
//            tv_jumlah?.text = "  x "+num
//            tv_total_harga_barang?.text = " Rp "+tot
//            tv_hrg?.text = " Rp "+tot
//            tv_note?.text = catatan
//        }else{
//            tv_item_detail?.text = ""
//        }

        var listProduk = keranjangSession.getKeranjangFull()!!

        for ((index, value) in listProduk.withIndex()) {
            println("the element at $index is $value")
        }


        var coba = 0
        listProduk.forEach {
            println(it.item.name)
            coba += it.total * it.item.price!!
        }
        println("-->"+coba)

        sharedPreference.save("total_hrg", coba.toString())
        tv_hrg.text = "Rp "+coba

        listAdapter = object : Adapter<ListItemKeranjang, ListCheckoutVH>(
            R.layout.item_list_checkout,
            arrayListOf(),
            ListCheckoutVH::class.java,
            ListItemKeranjang::class.java
        ){
            override fun bindView(holder: ListCheckoutVH, model: ListItemKeranjang, position: Int) {
                holder.bind(model)
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
        btn_bayar_akhir.setOnClickListener{
            bayarAkhir()
        }


    }

}
