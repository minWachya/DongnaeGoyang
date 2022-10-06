package com.example.dongnaegoyang.ui.cat_detail_info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailInfoResponse
import com.example.dongnaegoyang.data.remote.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailInfoViewModel @Inject constructor(
    private val repository: CatRepository
): ViewModel() {
    private val _catDetailInfoResponse = MutableLiveData<BaseResponse<CatDetailInfoResponse>>()
    val catDetailInfoResponse: LiveData<BaseResponse<CatDetailInfoResponse>> = _catDetailInfoResponse

    fun getCatDetailInfo(catIdx: Long)  = viewModelScope.launch {
        kotlin.runCatching {
            repository.getCatDetailInfo(catIdx)
        }.onSuccess {
            _catDetailInfoResponse.value = it
        }.onFailure {
            Log.d("mmm", " get cat detail info fail: ${it.message}")
        }
    }
}