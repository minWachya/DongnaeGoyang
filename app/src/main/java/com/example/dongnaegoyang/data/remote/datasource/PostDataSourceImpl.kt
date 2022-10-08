package com.example.dongnaegoyang.data.remote.datasource

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.PostListResponse
import com.example.dongnaegoyang.data.remote.service.PostService
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(private val postService: PostService) :
    PostDataSource {
    override suspend fun postCatPost(
        catIdx: Long,
        token: String,
        content: String
    ): BaseResponse<Unit> = postService.postCatPost(catIdx, token, content)

    override suspend fun deleteCatPost(postIdx: Long): BaseResponse<Unit> =
        postService.deleteCatPost(postIdx)

    override suspend fun getCatPost(catIdx: Long, page: Int): BaseResponse<PostListResponse> =
        postService.getCatPost(catIdx, page)

}