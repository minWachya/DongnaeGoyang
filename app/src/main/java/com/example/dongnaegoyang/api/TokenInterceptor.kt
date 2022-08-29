package com.example.dongnaegoyang.api

import android.util.Log
import com.example.dongnaegoyang.login.kakaoLogin.InitApplication
import com.example.dongnaegoyang.login.kakaoLogin.SharedPreferenceController
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
        Log.d("jh interceptor", "getUserToken "+ SharedPreferenceController.getAccessToken(InitApplication.getApplicationContext()))

        var token = SharedPreferenceController.getAccessToken(InitApplication.getApplicationContext())
        val newRequest = request().newBuilder()
            .addHeader("Authorization", "${token}")
            .build()
        proceed(newRequest)
    }
}