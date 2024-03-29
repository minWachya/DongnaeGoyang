package com.example.dongnaegoyang.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "http://3.36.144.189/v1/"

    //okhttp logging interceptor
    private var loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.e("", message)
        }
    }).apply { level = HttpLoggingInterceptor.Level.BODY } //로그 자세히

    private var httpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(TokenInterceptor())
        .build()

    //retrofit
    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient) //okhttp
            .addConverterFactory(GsonConverterFactory.create())
    }


    val apiService: AuthService by lazy {
        retrofit
            .build()
            .create(AuthService::class.java)
    }

}