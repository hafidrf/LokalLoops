package com.hafidrf.lokaloops.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.hafidrf.lokaloops.R

class MainActivity : AppCompatActivity(){

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, MainActivity::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val firstFragment = StoreFragment()
        openFragment(firstFragment)

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
                val storeFragment = StoreFragment()
                openFragment(storeFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_report -> {
                val reportFragment = HistoryFragment()
                openFragment(reportFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_account -> {
                val accountFragment = AccountFragment()
                openFragment(accountFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}