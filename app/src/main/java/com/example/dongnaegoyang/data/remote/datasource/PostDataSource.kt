package com.example.dongnaegoyang.data.remote.datasource

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.PostListResponse

interface PostDataSource {
    suspend fun postCatPost(catIdx: Long, token: String, content: String): BaseResponse<Unit>
    suspend fun deleteCatPost(postIdx: Long): BaseResponse<Unit>
    suspend fun getCatPost(catIdx: Long, page: Int): BaseResponse<PostListResponse>
}