package com.example.dongnaegoyang.ui.cat_detail.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.Post
import com.example.dongnaegoyang.data.remote.repository.PostRepository
import com.example.dongnaegoyang.ui.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailPostViewModel @Inject constructor(
    private val repository: PostRepository
): ViewModel() {
    private val _createPostResponse = MutableLiveData<BaseResponse<Int>>()
    val createPostResponse: LiveData<BaseResponse<Int>> = _createPostResponse
    private val _deletePostResponse = MutableLiveData<BaseResponse<Unit>>()
    val deletePostResponse: LiveData<BaseResponse<Unit>> = _deletePostResponse

    // 게시글 메뉴 클릭 여부
    private val _openPostEvent = MutableLiveData<Event<Long>>()
    val openPostEvent: LiveData<Event<Long>> = _openPostEvent

    suspend fun getPagingPost(catIdx: Long): Flow<PagingData<Post>> {
        return repository.getPagingPost(catIdx)
    }

    fun postCatPost(catIdx: Long, token: String, content: String)  = viewModelScope.launch {
        kotlin.runCatching {
            repository.postCatPost(catIdx, token, content)
        }.onSuccess {
            _createPostResponse.value = it
        }.onFailure {
            Log.d("mmm", " get cat detail create post fail: ${it.message}")
        }
    }

    fun deleteCatPost(postIdx: Long, token: String)  = viewModelScope.launch {
        kotlin.runCatching {
            repository.deleteCatPost(postIdx, token)
        }.onSuccess {
            _deletePostResponse.value = it
        }.onFailure {
            Log.d("mmm", " get cat detail delete post fail: ${it.message}")
        }
    }

    fun openPostMenu(postIdx: Long) {
        _openPostEvent.value = Event(postIdx)
    }
}