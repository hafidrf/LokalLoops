package com.hafidrf.lokaloops.utils

import android.content.Context
import android.content.SharedPreferences
import com.hafidrf.lokaloops.models.UserResponse

class PrefsUtil{

    constructor(context : Context?){
        prefs = context!!.getSharedPreferences("terserah", Context.MODE_PRIVATE)
    }

    private lateinit var prefs : SharedPreferences
    val SP_SUDAH_LOGIN = "spSudahLogin"

    fun saveUser(user : UserResponse){
        val editor = prefs.edit()
        editor.putString("username", user.username)
        editor.putString("password",user.role)
//        editor!!.commit()
        editor.apply()
    }

//    fun getValueString(user: UserResponse): SharedPreferences {
//        prefs.getString("username", user.username)
//        prefs.getString("username", user.role)
//        return prefs
//    }

    fun save(KEY_NAME: String, text: String) {

        val editor: SharedPreferences.Editor = prefs.edit()

        editor.putString(KEY_NAME, text)

        editor!!.commit()
    }
    fun getValueString(KEY_NAME: String): String? {

        return prefs.getString(KEY_NAME, null)
    }


    fun saveSPBoolean(KEY_NAME: String, value: Boolean) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putBoolean(KEY_NAME, value)
        editor.commit()
    }
    fun getSPSudahLogin(): Boolean? {
        return prefs.getBoolean(SP_SUDAH_LOGIN, false)
    }


}