package com.example.dongnaegoyang.api

import android.util.Log
import com.example.dongnaegoyang.login.kakaoLogin.InitApplication
import com.example.dongnaegoyang.login.kakaoLogin.SharedPreferenceController
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TokenInterceptor : Interceptor {
    companion object{
        const val X_AUTH_TOKEN = "X-AUTH-TOKEN"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
        Log.d("jh interceptor", "getUserToken "+ SharedPreferenceController.getToken(InitApplication.getApplicationContext()))

        val token = SharedPreferenceController.getToken(InitApplication.getApplicationContext())
        val newRequest = request().newBuilder()
            .addHeader(X_AUTH_TOKEN, token)
            .build()
        proceed(newRequest)
    }
}