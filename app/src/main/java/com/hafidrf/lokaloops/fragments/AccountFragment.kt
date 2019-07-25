package com.hafidrf.lokaloops.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.activities.LoginActivity
import com.hafidrf.lokaloops.utils.PrefsUtil
import kotlinx.android.synthetic.main.dialog_edit_password.*
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val prefs by lazy { PrefsUtil(activity) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nm = prefs.getValueString("username")
        val tvEmail = prefs.getValueString("email")
        val tvPassword = prefs.getValueString("password")
        val tvContact = prefs.getValueString("telp")
        val tvTentang = prefs.getValueString("tentang")
        val tvNama = prefs.getValueString("nama")


        tv_nama_login?.text = "Hai, " + nm
        tv_email_account?.text = tvEmail
        tv_password?.text = tvPassword
        tv_contact_account?.text = tvContact
        tv_role_login?.text = tvNama
        tv_about?.text = tvTentang

        btnLogout.setOnClickListener {
            prefs.saveSPBoolean(prefs.SP_SUDAH_LOGIN, false);
            val intent = Intent(activity, LoginActivity::class.java)
            activity!!.startActivity(intent)
        }

        val dialog = Dialog(context)

        btn_edit_password.setOnClickListener {
            dialog.setContentView(R.layout.dialog_edit_password)

            btn_change_ok?.setOnClickListener {
//                val passwot = dialog.et_change_password.text.toString()
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}