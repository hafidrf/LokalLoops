package com.hafidrf.lokaloops.viewholder

import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.models.ListItem
import com.hafidrf.lokaloops.models.NewPassResponse
import com.hafidrf.lokaloops.models.stockResponse
import com.hafidrf.lokaloops.rest.EndPoint
import com.hafidrf.lokaloops.rest.InterfacePoint
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.ListPesanan
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.dialog_edit_password.*
import kotlinx.android.synthetic.main.fragment_popup_order.*
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*


class ListItemVH(itemView: View) : RecyclerView.ViewHolder(itemView){

val sharedPreference : SharedPreference = SharedPreference(itemView.context)




    fun bind(data: ListItem, callback: Callback) {
        val keranjangSession: KeranjangSession = KeranjangSession(itemView.context)
        val sharedPreference: SharedPreference = SharedPreference(itemView.context)
         lateinit var tes: stockResponse

        val idp = sharedPreference.getValueString("id_pembeli")
        //format rupiah
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        var cekStock = 0
        val harga = data.price?.toDouble()
        val hargaBarangRp = formatRupiah.format(harga)

        itemView.tv_name?.text = data.name
        itemView.tv_stock?.text = "Stock : " + data.quantity
        itemView.tv_price?.text = hargaBarangRp
        cekStock = Integer.parseInt(data.quantity.toString())
        var stockIn = data.quantity
        itemView.iv_product?.apply {

            Glide.with(this)
                .load("https://saptorenggo-pakis.com/api/poslite/assets/gambar/" + data.foto)
                .into(this)

        }

//        val quan = Integer.parseInt(data.price.toString())

        itemView.bt_order?.setOnClickListener {

            val dialog = BottomSheetDialog(itemView.context)
            dialog.setContentView(R.layout.fragment_popup_order)
            dialog.tv_namabarang?.text = data.name + " " + hargaBarangRp
            val quan = Integer.parseInt(data.price.toString())
            var number = Integer.parseInt(dialog.tv_stock2.text.toString())
//            val subtotal = quan * number


            dialog.bt_min?.setOnClickListener {
                number -= 1
                if (number < 1) {
                    dialog.bt_min.isEnabled = false
                    dialog.btn_keranjang.setVisibility(View.GONE)
                    dialog.btn_backtomenu.setVisibility(View.VISIBLE)
                }
                dialog.et_note.text.toString()
                dialog.btn_keranjang.text = "Add to Box - " + formatRupiah.format(quan * number)
                dialog.tv_stock2.text = number.toString()
            }

            dialog.bt_plus?.setOnClickListener {
                if (number < cekStock) {
                    number += 1
                }else{
                    number = cekStock
                }

                if (number >= 1) {
                    dialog.bt_min.isEnabled = true
                    dialog.btn_backtomenu.setVisibility(View.GONE)
                    dialog.btn_keranjang.setVisibility(View.VISIBLE)
                    dialog.btn_keranjang.text = "Add to Box - " + formatRupiah.format(quan * number)
                }
                dialog.et_note.text.toString()
                dialog.tv_stock2.text = number.toString()
            }


            dialog.btn_keranjang?.setOnClickListener{
                var a = Integer.parseInt(data.quantity.toString())
                var stck = a - number

                val id = data.id.toString()
                val produk = data.name.toString()
                val price = data.price.toString()
                val num = number.toString()
                val catatan = dialog.et_note.text.toString()
//                sharedPreference.save("id", id)
//                sharedPreference.save("produk", produk)
//                sharedPreference.save("price", price)
//                sharedPreference.save("num", num)
//                sharedPreference.save("catatan", catatan)
//                sharedPreference.save("stock", stck.toString())
                keranjangSession.addProduct(data,num.toInt(),catatan)

                var totHrg = num.toInt() * data.price!!
                keranjangSession.addProductPesanan(data.name.toString() ,data.price.toString(),
                    num.toInt().toString(), totHrg.toString(),
                    catatan,idp.toString())

//                val upd = sharedPreference.getValueString("stock")!!
//                itemView.tv_stock?.text = "Stock : " + upd
                dialog.dismiss()


                EndPoint.client.create(InterfacePoint::class.java).saveStock(data.id.toString(), stck.toString()).enqueue(object:
                    retrofit2.Callback<stockResponse> {
                    override fun onResponse(call: Call<stockResponse>, response: Response<stockResponse>) {
                        if (response.isSuccessful) {
                            sharedPreference.save("stock",stockIn.toString())
                           println("berhasil")
                        } else {
                        }
                    }
                    override fun onFailure(call: Call<stockResponse>, t: Throwable) {
                        println("gagal")
                    }
                })
            }

            dialog.btn_backtomenu?.setOnClickListener{
                dialog.dismiss()
            }

            //dialog.setCancelable(false)
            dialog.show()
        }

    }

    interface Callback{
        fun onSubmit(data: com.hafidrf.lokaloops.models.ListItem, number:Int)
    }


}