package com.example.dongnaegoyang.data.remote.repository

import com.example.dongnaegoyang.data.remote.datasource.CatDataSource
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.response.CatDetailInfoResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailResponse
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val dataSource: CatDataSource
) : CatRepository {
    override suspend fun postCatAdd(token: String, body: CatAddRequest): BaseResponse<Int> =
        dataSource.postCatAdd(token, body)

    override suspend fun getCatDetail(token: String, catIdx: Long): BaseResponse<CatDetailResponse> =
        dataSource.getCatDetail(token, catIdx)

    override suspend fun getCatDetailInfo(catIdx: Long): BaseResponse<CatDetailInfoResponse> =
        dataSource.getCatDetailInfo(catIdx)
}