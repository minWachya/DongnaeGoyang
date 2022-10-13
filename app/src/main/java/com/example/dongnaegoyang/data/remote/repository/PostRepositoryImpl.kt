package com.example.dongnaegoyang.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.dongnaegoyang.data.remote.datasource.PostDataSource
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.Post
import com.example.dongnaegoyang.data.remote.model.response.PostListResponse
import com.example.dongnaegoyang.ui.cat_detail.post.PostPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val dataSource: PostDataSource
) : PostRepository {
    override suspend fun postCatPost(
        catIdx: Long,
        token: String,
        content: String
    ): BaseResponse<Int> = dataSource.postCatPost(catIdx, token, content)

    override suspend fun deleteCatPost(postIdx: Long, token: String): BaseResponse<Unit> =
        dataSource.deleteCatPost(postIdx, token)

    override suspend fun getCatPost(catIdx: Long, page: Int): BaseResponse<PostListResponse> =
        dataSource.getCatPost(catIdx, page)

    override suspend fun getPagingPost(catIdx: Long): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = { PostPagingSource(this, catIdx) }
        ).flow
    }

}