package com.example.dongnaegoyang.data.remote.service

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.request.CatModifyRequest
import com.example.dongnaegoyang.data.remote.model.response.CatDetailInfoResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailResponse
import com.example.dongnaegoyang.data.remote.model.response.CatModifyDataResponse
import retrofit2.http.*

interface CatService {
    // 고양이 추가
    @POST("v1/cats")
    suspend fun postCatAdd(@Header("X-AUTH-TOKEN") token: String,
                           @Body body: CatAddRequest)
    : BaseResponse<Int>

    // 고양이 수정 정보 받아오기
    @GET("v1/cats/{catIdx}")
    suspend fun getCatModifyData(@Header("X-AUTH-TOKEN") token: String,
                               @Path("catIdx") catIdx: Long)
            : BaseResponse<CatModifyDataResponse>

    // 고양이 수정 요청
    @PATCH("v1/cats/{catIdx}")
    suspend fun patchCatModify(@Header("X-AUTH-TOKEN") token: String,
                               @Path("catIdx") catIdx: Long,
                               @Body body: CatModifyRequest)
            : BaseResponse<Int>

    // 고양이 상세-기본
    @GET("v1/cats/{catIdx}/basic-info")
    suspend fun getCatDetail(@Header("X-AUTH-TOKEN") token: String,
                             @Path("catIdx") catIdx: Long)
            : BaseResponse<CatDetailResponse>
    // 고양이 상세-정보
    @GET("v1/cats/{catIdx}/additional-info")
    suspend fun getCatDetailInfo(@Path("catIdx") catIdx: Long)
            : BaseResponse<CatDetailInfoResponse>
}