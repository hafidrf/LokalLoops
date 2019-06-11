package com.hafidrf.lokaloops.viewholder

import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.models.ListItem
import com.hafidrf.lokaloops.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_popup_order.*
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast



class ListItemVH(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data: com.hafidrf.lokaloops.models.ListItem, callback: Callback) {
        val sharedPreference: SharedPreference = SharedPreference(itemView.context)


        itemView.tv_name?.text = data.name
        itemView.tv_stock?.text = "Stock : " + data.quantity
        itemView.tv_price?.text = "Rp. " + data.price

        itemView.iv_product?.apply {

            Glide.with(this)
                .load("https://saptorenggo-pakis.com/api/poslite/assets/gambar/" + data.foto)
                .into(this)

        }

//        val quan = Integer.parseInt(data.price.toString())

        itemView.bt_order?.setOnClickListener {

            val dialog = BottomSheetDialog(itemView.context)
            dialog.setContentView(R.layout.fragment_popup_order)
            dialog.tv_namabarang?.text = data.name + "  Rp. " + data.price
            val quan = Integer.parseInt(data.price.toString())
            var number = Integer.parseInt(dialog.tv_stock2.text.toString())


            dialog.bt_min?.setOnClickListener {
                number -= 1
                if (number < 1) {
                    dialog.bt_min.isEnabled = false
                    dialog.btn_keranjang.setVisibility(View.GONE)
                    dialog.btn_backtomenu.setVisibility(View.VISIBLE)
                }
                dialog.et_note.text.toString()
                dialog.btn_keranjang.text = "Add to Box - Rp. " + quan * number
                dialog.tv_stock2.text = number.toString()
            }

            dialog.bt_plus?.setOnClickListener {
                number +=1

                if (number >= 1) {
                    dialog.bt_min.isEnabled = true
                    dialog.btn_backtomenu.setVisibility(View.GONE)
                    dialog.btn_keranjang.setVisibility(View.VISIBLE)
                    dialog.btn_keranjang.text = "Add to Box - Rp. " + quan * number
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
                sharedPreference.save("id", id)
                sharedPreference.save("produk", produk)
                sharedPreference.save("price", price)
                sharedPreference.save("num", num)
                sharedPreference.save("catatan", catatan)
                sharedPreference.save("stock", stck.toString())
                val upd = sharedPreference.getValueString("stock")!!
                itemView.tv_stock?.text = "Stock : " + upd
                dialog.dismiss()

//                if (sharedPreference.getValueString("id").equals(id)){
//                    Log.e("data", data.toString())
//                }else {
//                    sharedPreference.save("id", id)
//                    sharedPreference.save("produk", produk)
//                    sharedPreference.save("price", price)
//                    sharedPreference.save("num", num)
//                    sharedPreference.save("catatan", catatan)
//                    dialog.dismiss()
//                }
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