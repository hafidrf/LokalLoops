package com.hafidrf.lokaloops.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.hafidrf.lokaloops.rest.EndPoint
import com.hafidrf.lokaloops.rest.InterfacePoint
import com.hafidrf.lokaloops.viewholder.ListitemRiwayat
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.history_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailHisFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailHisFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DetailHisFragment : Fragment() {

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, DetailHisFragment::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_his, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
