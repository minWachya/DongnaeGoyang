package com.example.dongnaegoyang.data.remote.repository

import com.example.dongnaegoyang.data.remote.datasource.PostDataSource
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.PostListResponse
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val dataSource: PostDataSource
) : PostRepository {
    override suspend fun postCatPost(
        catIdx: Long,
        token: String,
        content: String
    ): BaseResponse<Unit> = dataSource.postCatPost(catIdx, token, content)

    override suspend fun deleteCatPost(postIdx: Long): BaseResponse<Unit> =
        dataSource.deleteCatPost(postIdx)

    override suspend fun getCatPost(catIdx: Long, page: Int): BaseResponse<PostListResponse> =
        dataSource.getCatPost(catIdx, page)

}