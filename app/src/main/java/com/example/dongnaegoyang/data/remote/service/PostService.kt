package com.example.dongnaegoyang.data.remote.service

import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.PostListResponse
import retrofit2.http.*

interface PostService {
    // 게시글 등록
    @POST("v1/cats/{catIdx}/posts")
    suspend fun postCatPost(
        @Path("catIdx") catIdx: Long,
        @Header("X-AUTH-TOKEN") token: String,
        @Body content: String
    ): BaseResponse<Int>

    // 게시글 삭제
    @DELETE("v1/posts/{postIdx}")
    suspend fun deleteCatPost(
        @Path("postIdx") postIdx: Long,
        @Header("X-AUTH-TOKEN") token: String
    ): BaseResponse<Unit>

    // 게시글 목록 조회
    @GET("v1/cats/{catIdx}/posts")
    suspend fun getCatPost(
        @Path("catIdx") catIdx: Long,
        @Query("page") page: Int
    ): BaseResponse<PostListResponse>
}