package com.example.dongnaegoyang.login.kakaoLogin

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {
    fun getAccessToken(context: Context): String {
        val preference: SharedPreferences = context.getSharedPreferences("user info", Context.MODE_PRIVATE)
        return preference.getString("access token", "") ?: ""
    }


    fun getRefreshToken(context: Context): String {
        val preference: SharedPreferences = context.getSharedPreferences("user info", Context.MODE_PRIVATE)
        return preference.getString("refresh token", "") ?: ""
    }
}