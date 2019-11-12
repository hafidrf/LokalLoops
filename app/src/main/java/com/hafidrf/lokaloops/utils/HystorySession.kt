package com.hafidrf.lokaloops.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.hafidrf.lokaloops.models.ListHistory
import com.hafidrf.lokaloops.models.ListItem
import com.hafidrf.lokaloops.models.Pesanan
import org.json.JSONObject

class HystorySession (context: Context) {

    val prefs by lazy { context.getSharedPreferences("hystory-lokaloops", 0) }

    fun clearSharedPreference() {
        prefs.edit().clear().apply()
    }

    fun addProduct(product : ArrayList<Pesanan>, id: String){
//        val listTest = Gson().toJson(product)
        var exist = false
        val list = getKeranjangFull()
        product.forEach {
            for (i in 0 until list.size){
                if (list[i].item.id_pesan == it.id_pesan) {
                    exist = true
                    list[i] = ListItemHystory(it,id)
                }
            }
            if (!exist) list.add(ListItemHystory(it,id))
        }
        saveKeranjangFull(list)
    }

    fun getKeranjangFull (): ArrayList<ListItemHystory>{

        val typeList = object : TypeToken <ArrayList<ListItemHystory>>(){}.type
        val jsonList = prefs.getString("hystory-full", "null")


        val list = Gson().fromJson<ArrayList<ListItemHystory>>(jsonList, typeList) ?: return arrayListOf()

        return list

    }

    fun saveKeranjangFull(data : ArrayList<ListItemHystory>){
        val list = Gson().toJson(data)
        prefs.edit().apply {
            putString("hystory-full", list)
        }.apply()

        Log.e("Saved" , list.toString())

    }

}

data class ListItemHystory(
    val item: Pesanan,
    @SerializedName("id") @Expose val id: String
//    @SerializedName("nama_pembeli") @Expose val nama_pembeli: String,
//    @SerializedName("total_bayar") @Expose val total_bayar: String,
//    @SerializedName("uang_bayar") @Expose val uang_bayar: String,
//    @SerializedName("uang_kembali") @Expose val uang_kembali: String,
//    @SerializedName("tanggal") @Expose val tanggal: String
)

//fun addProduct(product : Pesanan, id:String, nama_pembeli: String,total_bayar: String,
//               uang_bayar: String,uang_kembali: String ,tanggal: String ){


