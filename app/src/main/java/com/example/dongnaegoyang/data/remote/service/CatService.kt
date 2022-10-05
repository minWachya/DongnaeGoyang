package com.example.dongnaegoyang.data.remote.service

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.response.AddressResponse
import retrofit2.http.*

interface CatService {
    @POST("v1/cats")
    suspend fun postCatAdd(@Header("X-AUTH-TOKEN") token: String,
                           @Body body: CatAddRequest)
    : BaseResponse<Int>
}