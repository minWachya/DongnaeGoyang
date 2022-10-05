package com.example.dongnaegoyang.data.remote.repository

import com.example.dongnaegoyang.data.remote.datasource.CatDataSource
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
                            private val dataSource: CatDataSource
): CatRepository {
    override suspend fun postCatAdd(token: String, body: CatAddRequest): BaseResponse<Int> =
        dataSource.postCatAdd(token, body)
}