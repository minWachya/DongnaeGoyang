package com.example.dongnaegoyang.data.remote.repository

import androidx.paging.PagingData
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.Post
import com.example.dongnaegoyang.data.remote.model.response.PostListResponse
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun postCatPost(catIdx: Long, token: String, content: String): BaseResponse<Int>
    suspend fun deleteCatPost(postIdx: Long, token: String): BaseResponse<Unit>
    suspend fun getCatPost(catIdx: Long, page: Int): BaseResponse<PostListResponse>
    suspend fun getPagingPost(catIdx: Long): Flow<PagingData<Post>>
}