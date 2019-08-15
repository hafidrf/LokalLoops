package com.hafidrf.lokaloops.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.fragments.StoreFragment
import com.hafidrf.lokaloops.models.ListItem
import com.hafidrf.lokaloops.viewholder.ListItemVH

class MainActivity : AppCompatActivity(), ListItemVH.Callback {

    override fun onSubmit(data: ListItem, number: Int) {
//        tv_name_check?.text = data.name
//        tv_price_check?.text = "Rp. " + data.price
//        tv_stock_check?.text = "Stock : " + number

    }

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, com.hafidrf.lokaloops.activities.MainActivity::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val sharedPreference: SharedPreference = SharedPreference(this)


        //bottom navbar
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val firstFragment = StoreFragment()
        openFragment(firstFragment)

        //bottom sheet

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {

            R.id.navigation_store -> {
                val storeFragment = com.hafidrf.lokaloops.fragments.StoreFragment()
                openFragment(storeFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_report -> {
                val reportFragment = com.hafidrf.lokaloops.fragments.HistoryFragment()
                openFragment(reportFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_account -> {
                val accountFragment = com.hafidrf.lokaloops.fragments.AccountFragment()
                openFragment(accountFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}