package com.example.dongnaegoyang.data.remote.datasource

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.request.CatModifyRequest
import com.example.dongnaegoyang.data.remote.model.response.CatDetailInfoResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailResponse
import com.example.dongnaegoyang.data.remote.model.response.CatModifyDataResponse
import com.example.dongnaegoyang.data.remote.service.CatService
import javax.inject.Inject

class CatDataSourceImpl @Inject constructor(private val catService: CatService) : CatDataSource {
    override suspend fun postCatAdd(token: String, body: CatAddRequest): BaseResponse<Int> =
        catService.postCatAdd(token, body)

    override suspend fun patchCatModify(token: String, catIdx: Long, body: CatModifyRequest): BaseResponse<Int> =
        catService.patchCatModify(token, catIdx, body)

    override suspend fun getCatModifyData(token: String, catIdx: Long): BaseResponse<CatModifyDataResponse> =
        catService.getCatModifyData(token, catIdx)

    override suspend fun getCatDetail(token: String, catIdx: Long): BaseResponse<CatDetailResponse> =
        catService.getCatDetail(token, catIdx)

    override suspend fun getCatDetailInfo(catIdx: Long): BaseResponse<CatDetailInfoResponse> =
        catService.getCatDetailInfo(catIdx)
}