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

class KeranjangSession (context: Context){

    val prefs by lazy { context.getSharedPreferences("keranjang-lokaloops", 0) }


    fun clearSharedPreference() {
        prefs.edit().clear().apply()
    }

    fun getPembeliFull (): ArrayList<ListPembeli>{

        val typeList = object : TypeToken <ArrayList<ListPembeli>>(){}.type
        val jsonList = prefs.getString("pembeli-full", "null")


        val list = Gson().fromJson<ArrayList<ListPembeli>>(jsonList, typeList) ?: return arrayListOf()

        return list

    }

    fun getPembeliserver() = prefs.getString("pembeli-full", "null")

    fun savePembeli(data: ArrayList<ListPembeli>){

        val list = Gson().toJson(data)

        prefs.edit().apply {
            putString("pembeli-full", list)
        }.apply()

    }

    fun addProductpembeli(id: String, nama_pembeli: String, total_bayar: String,
                   uang_bayar: String,uang_kembali: String, id_owner: String){

        var exist = false
        val list = getPembeliFull()
        for (i in 0 until list.size){
            if(list[i].id == id){
                exist = true
                list[i] = ListPembeli(id,nama_pembeli, total_bayar,
                    uang_bayar,uang_kembali, id_owner)
            }
        }
        if (!exist) list.add(ListPembeli(id,nama_pembeli, total_bayar,
            uang_bayar,uang_kembali, id_owner))
        savePembeli(list)
    }

    fun getKeranjangFull (): ArrayList<ListItemKeranjang>{

        val typeList = object : TypeToken <ArrayList<ListItemKeranjang>>(){}.type
        val jsonList = prefs.getString("keranjang-full", "null")


        val list = Gson().fromJson<ArrayList<ListItemKeranjang>>(jsonList, typeList) ?: return arrayListOf()

        return list

    }

    fun addProduct(product : ListItem, total:Int, catatan: String){

        var exist = false
        val list = getKeranjangFull()
        for (i in 0 until list.size){
            if(list[i].item.id == product.id){
                exist = true
                list[i] = ListItemKeranjang(product, total, catatan)
            }
        }
        if (!exist) list.add(ListItemKeranjang(product, total, catatan))
        saveKeranjangFull(list)
    }

    fun remProduct(position: Int){

        val list = getKeranjangFull()
        list.removeAt(position)
        saveKeranjangFull(list)
    }

    fun saveKeranjangFull(data : ArrayList<ListItemKeranjang>){
        val list = Gson().toJson(data)
        prefs.edit().apply {
            putString("keranjang-full", list)
        }.apply()

        Log.e("Saved" , list.toString())

    }

    fun getKeranjangServer() = prefs.getString("keranjang", "null")

    fun saveKeranjangServer(data: ArrayList<ListPesanan>){

        val list = Gson().toJson(data)

        prefs.edit().apply {
            putString("keranjang", list)
        }.apply()

    }

    fun getPesananFull (): ArrayList<ListPesanan>{

        val typeList = object : TypeToken <ArrayList<ListPesanan>>(){}.type
        val jsonList = prefs.getString("keranjang", "null")


        val list = Gson().fromJson<ArrayList<ListPesanan>>(jsonList, typeList) ?: return arrayListOf()

        return list

    }

    fun addProductPesanan(item: String, harga: String,jumlah_pesan: String,total_harga: String,
                          catatan: String,id_pembeli: String){

//            var exist = false
            val list = getPesananFull()
//            for (i in 0 until list.size){
//                if(list[i].id == id){
//                    exist = true
//                    list[i] = ListPesanan( item, harga,jumlah_pesan,
//                        total_harga,catatan,id_pembeli)
//                }
//            }
//            if (!exist)
            list.add(ListPesanan(item, harga,jumlah_pesan, total_harga,catatan,id_pembeli))
            saveKeranjangServer(list)
        }

}

data class ListPembeli(
    @SerializedName("id") @Expose val id: String?,
    @SerializedName("nama_pembeli") @Expose val nama_pembeli: String?,
    @SerializedName("total_bayar") @Expose val total_bayar: String?,
    @SerializedName("uang_bayar") @Expose val uang_bayar: String?,
    @SerializedName("uang_kembali") @Expose val uang_kembali: String?,
    @SerializedName("id_owner") @Expose val id_owner: String?
)

data class ListItemKeranjang(
    val item: ListItem,
    @SerializedName("total") @Expose val total: Int?,
    @SerializedName("catatan") @Expose val catatan: String
)

data class ListPesanan(
//    @SerializedName("id") @Expose val id : String,
    @SerializedName("item") @Expose val item : String,
    @SerializedName("harga") @Expose val harga : String,
    @SerializedName("jumlah_pesan") @Expose val jumlah_pesan: String,
    @SerializedName("total_harga") @Expose val total_harga: String,
    @SerializedName("catatan") @Expose val catatan: String,
    @SerializedName("id_pembeli") @Expose val id_pembeli: String

)


