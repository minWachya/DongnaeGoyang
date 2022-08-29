package com.example.dongnaegoyang.api

import com.example.dongnaegoyang.login.ModelLoginSignUpResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    //로그인
    @POST("users/login")
    fun postLogin(): Call<ModelLoginSignUpResponseData>

    //회원가입
    @POST("users/signUp")
    fun postSignUp(
        @Body params: HashMap<String, String>
    ): Call<ModelLoginSignUpResponseData>
}