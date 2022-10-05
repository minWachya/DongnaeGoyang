package com.example.dongnaegoyang.data.remote.repository

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest

interface CatRepository {
    suspend fun postCatAdd(token: String, body: CatAddRequest): BaseResponse<Int>
}