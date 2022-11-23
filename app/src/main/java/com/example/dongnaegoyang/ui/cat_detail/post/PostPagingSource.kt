package com.example.dongnaegoyang.ui.cat_detail.post

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dongnaegoyang.data.remote.model.response.Post
import com.example.dongnaegoyang.data.remote.repository.PostRepository
import javax.inject.Inject

class PostPagingSource @Inject constructor(
    private val repository: PostRepository,
    val catIdx: Long
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val pageIndex = params.key ?: 0
            val postList = repository.getCatPost(catIdx, pageIndex).data.postList

            LoadResult.Page(
                data = postList,
                nextKey = null, //if (pageIndex == 0) null else pageIndex,
                prevKey = if (postList.isEmpty()) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return 0/*state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }*/
    }
}