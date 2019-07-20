package com.hafidrf.lokaloops.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.fragments.AccountFragment
import com.hafidrf.lokaloops.fragments.DoneFragment
import com.hafidrf.lokaloops.fragments.KitchenFragment

class Main2Activity : AppCompatActivity() {

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, Main2Activity::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //bottom navbar
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationViewKitchen)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavItemSelectedListener)
        val firstFragment = KitchenFragment()
        openFragment(firstFragment)

        //bottom sheet

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container2, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private val mOnNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {

            R.id.navigation_kitchen -> {
                val accountFragment = KitchenFragment()
                openFragment(accountFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_done -> {
                val doneFragment = DoneFragment()
                openFragment(doneFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_account2 -> {
                val accountFragment = AccountFragment()
                openFragment(accountFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
