package com.hafidrf.lokaloops.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.models.Adapter
import com.hafidrf.lokaloops.utils.ListItemKeranjang
import com.hafidrf.lokaloops.viewholder.ListCheckoutVH


class TransaksiActivity : AppCompatActivity() {

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, TransaksiActivity::class.java)

    }

    private lateinit var listAdapter : Adapter<ListItemKeranjang, ListCheckoutVH>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)


        val firstFragment = com.hafidrf.lokaloops.fragments.Checkout()
        openFragment(firstFragment)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun kembali(){
        finish()
    }

}
