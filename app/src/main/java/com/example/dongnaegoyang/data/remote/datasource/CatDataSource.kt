package com.example.dongnaegoyang.data.remote.datasource

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.request.CatModifyRequest
import com.example.dongnaegoyang.data.remote.model.response.CatDetailInfoResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailResponse
import com.example.dongnaegoyang.data.remote.model.response.CatModifyDataResponse

interface CatDataSource {
    suspend fun postCatAdd(token: String, body: CatAddRequest): BaseResponse<Int>
    suspend fun patchCatModify(token: String, catIdx: Long, body: CatModifyRequest): BaseResponse<Int>
    suspend fun getCatModifyData(token: String, catIdx: Long): BaseResponse<CatModifyDataResponse>
    suspend fun getCatDetail(token: String, catIdx: Long): BaseResponse<CatDetailResponse>
    suspend fun getCatDetailInfo(catIdx: Long): BaseResponse<CatDetailInfoResponse>
}