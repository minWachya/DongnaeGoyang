package com.example.dongnaegoyang.data.remote.datasource

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest

interface CatDataSource {
    suspend fun postCatAdd(token: String, body: CatAddRequest): BaseResponse<Int>
}