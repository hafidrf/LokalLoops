package com.hafidrf.lokaloops.utils

import android.content.Context
import android.content.SharedPreferences
import com.hafidrf.lokaloops.models.UserResponse

class PrefsUtil{

    constructor(context : Context?){
        prefs = context!!.getSharedPreferences("terserah", Context.MODE_PRIVATE)
    }

    private lateinit var prefs : SharedPreferences

    fun saveUser(KEY_NAME: String, user : com.hafidrf.lokaloops.models.UserResponse){
        val editor = prefs.edit()
        editor.putString("username", user.username)
        editor.putString("password",user.password)
        editor!!.commit()
    }

//    fun getValueString(KEY_NAME: String, user: UserResponse): SharedPreferences {
//        prefs.getString(KEY_NAME, user.username)
//        return prefs
//    }


}