package com.example.theshoestore.helper

import android.content.Context
import android.content.SharedPreferences
import java.lang.NullPointerException

class PreferenceHelper {

    private lateinit var app_prefs : SharedPreferences
    private val email = "Email"

    constructor(context: Context?){
        try {
            if (context != null) {
                app_prefs = context.getSharedPreferences("shoeApp", Context.MODE_PRIVATE)
            }
        } catch (e: NullPointerException) {
        }
    }

    fun getEmail(): String? {
        return app_prefs!!.getString(email, null)
    }

    fun setEmail(API_Email: String?) {
        val edit = app_prefs!!.edit()
        edit.putString(email, API_Email)
        edit.apply()
    }
}