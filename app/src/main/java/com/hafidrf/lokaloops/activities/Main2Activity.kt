package com.hafidrf.lokaloops.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.hafidrf.lokaloops.R
import com.hafidrf.lokaloops.fragments.DoneFragment
import com.hafidrf.lokaloops.fragments.KitchenFragment

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //bottom navbar
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationViewKitchen)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val firstFragment = KitchenFragment()
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
                val accountFragment = KitchenFragment()
                openFragment(accountFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_report -> {
                val doneFragment = DoneFragment()
                openFragment(doneFragment)
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
