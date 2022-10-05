package com.example.dongnaegoyang.data.remote.datasource

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.service.CatService
import javax.inject.Inject

class CatDataSourceImpl @Inject constructor(private val catService: CatService) : CatDataSource {
    override suspend fun postCatAdd(token: String, body: CatAddRequest): BaseResponse<Int> =
        catService.postCatAdd(token, body)
}