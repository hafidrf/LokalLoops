package com.hafidrf.lokaloops.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.models.UserResponse
import com.hafidrf.lokaloops.rest.EndPoint
import com.hafidrf.lokaloops.rest.InterfacePoint
import com.hafidrf.lokaloops.utils.PrefsUtil
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    private val prefs by lazy { PrefsUtil(this) }
    private lateinit var iPoint: InterfacePoint
    private lateinit var user: com.hafidrf.lokaloops.models.UserResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        setupToolbar()

        iPoint = EndPoint.client.create(InterfacePoint::class.java)
        btn_login.setOnClickListener {
            login()
        }
    }

//    private fun setupToolbar() {
//        val actionBar = supportActionBar
//        actionBar!!.hide()
//    }

    private fun login() {
        iPoint.login(et_email.text.toString(), et_password.text.toString()).enqueue(object : Callback<com.hafidrf.lokaloops.models.UserResponse> {
            override fun onFailure(call: Call<com.hafidrf.lokaloops.models.UserResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Password atau username salah", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<com.hafidrf.lokaloops.models.UserResponse>, response: Response<com.hafidrf.lokaloops.models.UserResponse>) {
                if (response.isSuccessful) {
                    user = response.body()!!
//                    prefs.saveUser(user)
                    prefs.save("username",user.username)
                    prefs.save("role",user.role)
                    prefs.save("password",user.password)
                    prefs.save("telp", user.telp)
                    prefs.save("email", user.email)
                    prefs.save("tentang", user.tentang)
                    prefs.save("nama", user.nama)
                    if (user.role == "owner") {
//                    prefs.save("username",user)
//                    prefs.saveUser("login",user)
                        startActivity(MainActivity.getIntent(this@LoginActivity))
                        finish()
                    }else if(user.role=="kasir"){
                        startActivity(Main2Activity.getIntent(this@LoginActivity))
                        finish()
                    }else{

                    }
                } else {
                }

            }

        })
    }


}
