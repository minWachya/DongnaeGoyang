package com.example.dongnaegoyang.data.remote.model

/*
{
    "timeStamp": "2022-10-03 15:38:24",
    "status": 201,
    "message": "success createCat",
    "data": T
}
*/

data class BaseResponse<T>(
    val timeStamp: String,
    val status: Int,
    val message: String,
    val data: T
)