package com.hafidrf.lokaloops.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hafidrf.lokaloops.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object : Thread() {
            override fun run() {
                try {
                    sleep(2500)
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }

        background.start()
    }
}
