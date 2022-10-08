package com.example.dongnaegoyang.ui.cat_detail.info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailInfoResponse
import com.example.dongnaegoyang.data.remote.repository.CatRepository
import com.example.dongnaegoyang.ui.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailInfoViewModel @Inject constructor(
    private val repository: CatRepository
): ViewModel() {
    private val _catDetailInfoResponse = MutableLiveData<BaseResponse<CatDetailInfoResponse>>()
    val catDetailInfoResponse: LiveData<BaseResponse<CatDetailInfoResponse>> = _catDetailInfoResponse

    // 고양이 클릭 여부
    private val _openCatDetailEvent = MutableLiveData<Event<Long>>()
    val openCatDetailEvent: LiveData<Event<Long>> = _openCatDetailEvent

    fun getCatDetailInfo(catIdx: Long)  = viewModelScope.launch {
        kotlin.runCatching {
            repository.getCatDetailInfo(catIdx)
        }.onSuccess {
            _catDetailInfoResponse.value = it
        }.onFailure {
            Log.d("mmm", " get cat detail info fail: ${it.message}")
        }
    }

    fun openCatDetail(postIdx: Long) {
        _openCatDetailEvent.value = Event(postIdx)
    }
}