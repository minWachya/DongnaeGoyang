package com.example.dongnaegoyang.api

import com.example.dongnaegoyang.login.ModelLoginResponse
import com.example.dongnaegoyang.login.ModelSignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    companion object{
        val LOGIN_TYPE_KAKAO = mutableMapOf<String, String>(Pair("loginType", "kakao"))
        const val AUTHORIZATION = "Authorization"
    }

    //로그인
    @POST("members/login")
    fun postLoginKakao(
        @Header(AUTHORIZATION) header: String,
        @Body params: MutableMap<String, String> = LOGIN_TYPE_KAKAO
    ): Call<ModelLoginResponse>

    //회원가입
    @POST("members/signUp")
    fun postSignUp(
        @Header(AUTHORIZATION) header: String,
        @Body params: HashMap<String, String>
    ): Call<ModelSignUpResponse>
}