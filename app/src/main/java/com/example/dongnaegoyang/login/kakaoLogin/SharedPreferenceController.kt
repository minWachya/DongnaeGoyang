package com.example.dongnaegoyang.login.kakaoLogin

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {
    private const val USER_INFO = "user info"
    private const val ACCESS_TOKEN = "access token"
    private const val SIDO = "sido"
    private const val GUGUN = "gugun"
    private const val NICKNAME = "nickname"

    private const val MODE = "mode"
    private const val MODE_LOGIN = "login"
    private const val MODE_LOG_OUT = "log out"

    //token
    fun saveToken(context: Context, token: String) {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        preference.edit().putString(ACCESS_TOKEN, token).apply()
    }

    fun getToken(context: Context): String {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        return preference.getString(ACCESS_TOKEN, "") ?: ""
    }

    //sido
    fun saveSido(context: Context, sido: String) {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        preference.edit().putString(SIDO, sido).apply()
    }

    fun getSido(context: Context): String {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        return preference.getString(SIDO, "") ?: ""
    }

    //gugun
    fun saveGugun(context: Context, gugun: String) {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        preference.edit().putString(GUGUN, gugun).apply()
    }

    fun getGugun(context: Context): String {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        return preference.getString(GUGUN, "") ?: ""
    }

    //nickname
    fun saveNickname(context: Context, nickname: String) {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        preference.edit().putString(NICKNAME, nickname).apply()
    }

    fun getNickname(context: Context): String {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        return preference.getString(NICKNAME, "") ?: ""
    }

    //mode
    fun setModeLogin(context: Context) {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        preference.edit().putString(MODE, MODE_LOGIN)
    }

    fun setModeLogOut(context: Context) {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        preference.edit().putString(MODE, MODE_LOG_OUT)
    }

    fun getMode(context: Context): String {
        val preference: SharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        return preference.getString(MODE, "") ?: ""
    }



//    fun getRefreshToken(context: Context): String {
//        val preference: SharedPreferences = context.getSharedPreferences("user info", Context.MODE_PRIVATE)
//        return preference.getString("refresh token", "") ?: ""
//    }
}