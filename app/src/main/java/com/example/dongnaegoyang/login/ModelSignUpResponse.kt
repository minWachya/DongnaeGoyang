package com.example.dongnaegoyang.login

data class ModelSignUpResponse(
    val timeStamp: String,
    val status: Int,
    val errorCode: String,
    val message: String,
    val data: String?
)
