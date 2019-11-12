package com.hafidrf.lokaloops.ui.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hafidrf.lokaloops.network.EndPoint
import com.hafidrf.lokaloops.network.InterfacePoint
import com.hafidrf.lokaloops.utils.PrefsUtil
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.text.method.LinkMovementMethod
import android.text.Html
import com.hafidrf.lokaloops.ui.main.MainActivity


class LoginActivity : AppCompatActivity() {

    private val prefs by lazy { PrefsUtil(this) }
    private lateinit var iPoint: InterfacePoint
    private lateinit var user: com.hafidrf.lokaloops.models.UserResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.hafidrf.lokaloops.R.layout.activity_login)

        val iLogin = prefs.getValueString("role")
        if (prefs.getSPSudahLogin()== true) {
            if (iLogin == "owner") {
                startActivity(MainActivity.getIntent(this@LoginActivity))
                finish()
            }else{

            }
        }else {
            iPoint = EndPoint.client.create(InterfacePoint::class.java)
            btn_login.setOnClickListener {
                login()
            }
        }

        link_signup.isClickable = true
        link_signup.movementMethod = LinkMovementMethod.getInstance()
        val link = "<a href='http://hafidrf.com/lokaloops/register/'> Daftar disini </a>"
        link_signup.text = Html.fromHtml(link)
    }

    private fun login() {
        iPoint.login(et_email.text.toString(), et_password.text.toString()).enqueue(object : Callback<com.hafidrf.lokaloops.models.UserResponse> {
            override fun onFailure(call: Call<com.hafidrf.lokaloops.models.UserResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Password atau username salah", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<com.hafidrf.lokaloops.models.UserResponse>, response: Response<com.hafidrf.lokaloops.models.UserResponse>) {
                if (response.isSuccessful) {
                    user = response.body()!!
                    prefs.save("id_owner", user.id_owner)
                    prefs.save("username",user.username)
                    prefs.save("role",user.role)
                    prefs.save("password",user.password)
                    prefs.save("telp", user.telp)
                    prefs.save("email", user.email)
                    prefs.save("tentang", user.tentang)
                    prefs.save("nama", user.nama)
                    prefs.save("id", user.id)
                    prefs.saveSPBoolean(prefs.SP_SUDAH_LOGIN, true);
                    if (user.role == "owner") {
                        startActivity(
                            MainActivity.getIntent(
                                this@LoginActivity
                            )
                        )
                        finish()

                    }
                } else {
                }

            }

        })
    }

}
