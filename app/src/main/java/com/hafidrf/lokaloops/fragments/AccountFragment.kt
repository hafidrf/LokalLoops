package com.hafidrf.lokaloops.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.activities.LoginActivity
import com.hafidrf.lokaloops.models.NewPassResponse
import com.hafidrf.lokaloops.models.UserResponse
import com.hafidrf.lokaloops.rest.EndPoint
import com.hafidrf.lokaloops.rest.InterfacePoint
import com.hafidrf.lokaloops.utils.PrefsUtil
import kotlinx.android.synthetic.main.dialog_edit_password.*
import kotlinx.android.synthetic.main.fragment_account.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val prefs by lazy { PrefsUtil(activity) }
    private lateinit var iPoint: InterfacePoint
    private lateinit var user: com.hafidrf.lokaloops.models.UserResponse
    private lateinit var newPass: NewPassResponse



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idUser = prefs.getValueString("id")
        val nm = prefs.getValueString("username")
        val tvEmail = prefs.getValueString("email")
        val tvPassword = prefs.getValueString("password")
        val tvContact = prefs.getValueString("telp")
        val tvTentang = prefs.getValueString("tentang")
        val tvNama = prefs.getValueString("nama")

//        for (i in 1..tvPassword!!.length)
//            println("*")


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

        val dialog = Dialog(context!!)

        btn_edit_password?.setOnClickListener {
            println("hello world"+idUser.toString())
            dialog.setContentView(R.layout.dialog_edit_password)
            dialog.btn_change_ok?.setOnClickListener {
                println("hello world")
                //CEK PASSWORD SEBELUM DIKIRIM
                if (dialog.et_old_password.text.toString().equals(tvPassword.toString())){

                    if (dialog.et_new_password.text.toString().equals(dialog.et_confirm_password.text.toString())){
                        println("hello world"+dialog.et_confirm_password.text.toString())
                        //KIRIM PASSWORD
                        EndPoint.client.create(InterfacePoint::class.java).kirimPass(idUser.toString(), dialog.et_confirm_password.text.toString()).enqueue(object: Callback<NewPassResponse>{
                           override fun onResponse(call: Call<NewPassResponse>, response: Response<NewPassResponse>) {
                                if (response.isSuccessful) {
                                    newPass = response.body()!!
                                    prefs.save("password",newPass.password)
                                    Toast.makeText(activity, "Berhasil", Toast.LENGTH_LONG).show()
                                    dialog.dismiss()
                                } else {
                                }
                            }
                            override fun onFailure(call: Call<NewPassResponse>, t: Throwable) {
                                Toast.makeText(activity, "Gagal mengganti password", Toast.LENGTH_LONG).show()
                            }

                        })

                    }else {
                        Toast.makeText(activity, "Periksa kembali password baru anda", Toast.LENGTH_LONG).show()
                        println("salah konfirmasi")
                    }
                } else {
                    Toast.makeText(activity, "Passord lama salah", Toast.LENGTH_LONG).show()
                    println("salah pass lama")
                }
            }
            dialog.show()
        }

    }

}