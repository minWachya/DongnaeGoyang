package com.example.dongnaegoyang.data.remote.repository

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.response.CatDetailInfoResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailResponse

interface CatRepository {
    suspend fun postCatAdd(token: String, body: CatAddRequest): BaseResponse<Int>
    suspend fun getCatDetail(catIdx: Long): BaseResponse<CatDetailResponse>
    suspend fun getCatDetailInfo(catIdx: Long): BaseResponse<CatDetailInfoResponse>
}