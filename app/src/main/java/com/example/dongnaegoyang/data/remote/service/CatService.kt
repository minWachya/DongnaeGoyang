package com.example.dongnaegoyang.data.remote.service

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.response.CatDetailInfoResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailResponse
import retrofit2.http.*

interface CatService {
    // 고양이 추가
    @POST("v1/cats")
    suspend fun postCatAdd(@Header("X-AUTH-TOKEN") token: String,
                           @Body body: CatAddRequest)
    : BaseResponse<Int>

    // 고양이 상세-기본
    @GET("/v1/cats/{catIdx}/basic-info")
    suspend fun getCatDetail(@Path("catIdx") catIdx: Long)
            : BaseResponse<CatDetailResponse>
    // 고양이 상세-정보
    @GET("/v1/cats/{catIdx}/additional-info")
    suspend fun getCatDetailInfo(@Path("catIdx") catIdx: Long)
            : BaseResponse<CatDetailInfoResponse>
}