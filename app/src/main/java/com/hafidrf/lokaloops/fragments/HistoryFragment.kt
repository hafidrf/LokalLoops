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
import com.hafidrf.lokaloops.models.Adapter
import com.hafidrf.lokaloops.models.ListHistory
import com.hafidrf.lokaloops.models.ListHistoryResponse
import com.hafidrf.lokaloops.models.ListItem
import com.hafidrf.lokaloops.rest.EndPoint
import com.hafidrf.lokaloops.rest.InterfacePoint
import com.hafidrf.lokaloops.viewholder.ListitemRiwayat
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.history_list.*
import kotlinx.android.synthetic.main.item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.LinearLayout
import com.hafidrf.lokaloops.activities.DetailHistoryActivity
import org.jetbrains.anko.support.v4.intentFor


class HistoryFragment : Fragment(), ListitemRiwayat.Callback {

    override fun onClick(data: ListHistory) {
       startActivity(intentFor<DetailHistoryActivity>() )
    }

    override fun onSubmit(data: ListHistory, number: Int) {
        println("google coy cou")
        println(data.nama_pembeli)
    }

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, com.hafidrf.lokaloops.fragments.HistoryFragment::class.java)

    }

//    fun next(){
//        val detailhf = DetailHisFragment()
//        val fragmentManager = fragmentManager
//        val fragmentTransaction = fragmentManager!!.beginTransaction()
//        fragmentTransaction.replace(R.id.container, detailhf)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
//    }

    private lateinit var listAdapter : Adapter<ListHistory, ListitemRiwayat>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        ln_his?.setOnClickListener {
//            next()
//        }

        listAdapter = object : com.hafidrf.lokaloops.models.Adapter<ListHistory, ListitemRiwayat>(
            R.layout.history_list,
            arrayListOf(),
            ListitemRiwayat::class.java,
            com.hafidrf.lokaloops.models.ListHistory::class.java
        ){
            override fun bindView(holder: ListitemRiwayat, model: ListHistory, position: Int) {
                holder.bind(model, this@HistoryFragment)
            }

        }

        rv_list_history?.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpaceItem(context, 1f, 1f))
        }

        EndPoint.client.create(InterfacePoint::class.java).listHistory().enqueue(object : Callback<ListHistoryResponse> {
            override fun onResponse(call: Call<ListHistoryResponse>, response: Response<ListHistoryResponse>) {

                if(response.isSuccessful) listAdapter.updateList(response.body()!!.result)

            }

            override fun onFailure(call: Call<ListHistoryResponse>, t: Throwable) {

            }
        })


    }

}
