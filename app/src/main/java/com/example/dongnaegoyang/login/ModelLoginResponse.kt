package com.example.dongnaegoyang.login

data class ModelLoginResponse(
    val timeStamp: String,
    val status: Int,
    val message: String,
    val data: LoginResponseData?
)

data class LoginResponseData(
    val token: String,
    val nickname: String,
    val sido: String,
    val gugun: String
)
