package com.hafidrf.lokaloops.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hafidrf.lokaloops.R
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val sharedPreference: PrefsUtil = PrefsUtil(view.context)

       // val nm = sharedPreference.getValueString("username", UserResponse())
        tv_nama_login?.text = "Hai, admin"
    }
}