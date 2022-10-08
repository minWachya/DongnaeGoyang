package com.example.dongnaegoyang.data.remote.repository

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.response.CatDetailInfoResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailResponse
import com.example.dongnaegoyang.data.remote.model.response.PostListResponse

interface PostRepository {
    suspend fun postCatPost(catIdx: Long, token: String, content: String): BaseResponse<Int>
    suspend fun deleteCatPost(postIdx: Long, token: String): BaseResponse<Unit>
    suspend fun getCatPost(catIdx: Long, page: Int): BaseResponse<PostListResponse>
}