package com.hafidrf.lokaloops.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.common.VerticalSpaceItem
import com.hafidrf.lokaloops.models.*
import com.hafidrf.lokaloops.rest.EndPoint
import com.hafidrf.lokaloops.rest.InterfacePoint
import com.hafidrf.lokaloops.utils.SharedPreference
import com.hafidrf.lokaloops.viewholder.ListItemVH
import kotlinx.android.synthetic.main.fragment_store.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list_checkout.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.hafidrf.lokaloops.rest.database
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.view.KeyEvent
import android.widget.EditText
import com.hafidrf.lokaloops.activities.Print_factur
import com.hafidrf.lokaloops.activities.TransaksiActivity


class StoreFragment : Fragment(), ListItemVH.Callback {

    override fun onSubmit(data: com.hafidrf.lokaloops.models.ListItem, number: Int) {
        tv_name?.text = data.name
        tv_stock?.text = number.toString()
    }



    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, com.hafidrf.lokaloops.fragments.StoreFragment::class.java)

    }

    private lateinit var listAdapter : com.hafidrf.lokaloops.models.Adapter<com.hafidrf.lokaloops.models.ListItem, ListItemVH>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_store, container, false)

    }

    fun ngaleh(){
        val intent = Intent (activity, TransaksiActivity::class.java)
        activity!!.startActivity(intent)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_bayar.setOnClickListener{
            ngaleh()
        }

        btn_cari.setOnClickListener{
            val ap = Cari()

            //akses setter untuk mengatur nilainya
            ap.name = et_pencarian?.text.toString()

            EndPoint.client.create(InterfacePoint::class.java).cariData(ap.name).enqueue(object : Callback<ListItemResponse> {
                override fun onResponse(call: Call<ListItemResponse>, response: Response<ListItemResponse>) {

                    if(response.isSuccessful) listAdapter.updateList(response.body()!!.result)

                }

                override fun onFailure(call: Call<ListItemResponse>, t: Throwable) {

                }
            })
        }

        listAdapter = object : com.hafidrf.lokaloops.models.Adapter<ListItem, ListItemVH>(
            R.layout.item_list,
            arrayListOf(),
            ListItemVH::class.java,
            com.hafidrf.lokaloops.models.ListItem::class.java
        ){
            override fun bindView(holder: ListItemVH, model: ListItem, position: Int) {
                holder.bind(model, this@StoreFragment)
            }

        }

        rv_list_item?.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpaceItem(context, 5f, 2f))
        }

        EndPoint.client.create(InterfacePoint::class.java).listItem().enqueue(object : Callback<ListItemResponse> {
            override fun onResponse(call: Call<ListItemResponse>, response: Response<ListItemResponse>) {

                if(response.isSuccessful) listAdapter.updateList(response.body()!!.result)

            }

            override fun onFailure(call: Call<ListItemResponse>, t: Throwable) {

            }
        })


    }
}
