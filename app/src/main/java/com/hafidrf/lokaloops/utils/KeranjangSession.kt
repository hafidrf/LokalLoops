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
import com.hafidrf.lokaloops.models.ListItem
import org.json.JSONObject

class KeranjangSession (context: Context){

    val prefs by lazy { context.getSharedPreferences("keranjang-lokaloops", 0) }

    fun saveKeranjangFull(data : ArrayList<ListItemKeranjang>){
        val list = Gson().toJson(data)
        prefs.edit().apply {
            putString("keranjang-full", list)
        }.apply()

        Log.e("Saved" , list.toString())

        val listFood = arrayListOf<Food>()

        for(item in data){
            listFood.add(Food(item.item.id!!, item.total, item.catatan))
        }
        saveKeranjangServer(listFood)
        Log.e("ini" , listFood.toString())

    }

    fun saveKeranjangServer(data: ArrayList<Food>){

        val list = Gson().toJson(data)

//        val jsonygdikirim = JSONObject().apply {
//
//            put("id_user", 2882828282)
//            put("kersnjsng", getKeranjangServer())
//
//        }.toString()

        prefs.edit().apply {
            putString("keranjang", list)
        }.apply()

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


    fun remProduct(product : ListItem, total:Int, catatan: String){

        val list = getKeranjangFull()
        for (i in 0 until list.size){
            if(list[i].item.id == product.id){
                list.removeAt(i)
            }
        }
        saveKeranjangFull(list)
    }

//    fun coba(): ArrayList<ListItemKeranjang>{
//        val typeList = object : TypeToken <ArrayList<ListItemKeranjang>>(){}.type
//        val jsonList = prefs.getString("keranjang-full", "null")
//
//
//        val list = Gson().fromJson<ArrayList<ListItemKeranjang>>(jsonList, typeList) ?: return arrayListOf()
//
//        var sum = 0.0
//        for (i in 0 until list.size)
//            list.get(2)
//
//        return list
//    }

    fun getKeranjangFull (): ArrayList<ListItemKeranjang>{

        val typeList = object : TypeToken <ArrayList<ListItemKeranjang>>(){}.type
        val jsonList = prefs.getString("keranjang-full", "null")


        val list = Gson().fromJson<ArrayList<ListItemKeranjang>>(jsonList, typeList) ?: return arrayListOf()

        return list

    }

    fun getKeranjangServer() = prefs.getString("keranjang", "null")

    /*

    {
        "id_user" : 202020
        "keranjang" :


        [
            {  "id" : 1, "total" : 2   },
            {  "id" : 1, "total" : 2   }
        ]


    }
*/

    fun clearSharedPreference() {

        val editor: SharedPreferences.Editor = prefs.edit()

        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        editor.clear()
        editor.commit()
    }


}


data class Food(
    @SerializedName("id") @Expose val id : String,
    @SerializedName("total") @Expose val total : Int = 0,
    @SerializedName("catatan") @Expose val catatan: String
)

data class ListItemKeranjang(
    val item: ListItem,
    @SerializedName("total") @Expose val total: Int,
    @SerializedName("catatan") @Expose val catatan: String
)