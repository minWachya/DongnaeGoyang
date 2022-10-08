package com.example.dongnaegoyang.ui.cat_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.response.CatDetailResponse
import com.example.dongnaegoyang.data.remote.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(
    private val repository: CatRepository
): ViewModel() {
    private val _catDetailResponse = MutableLiveData<BaseResponse<CatDetailResponse>>()
    val catDetailResponse: LiveData<BaseResponse<CatDetailResponse>> = _catDetailResponse

    fun getCatDetail(token: String, catIdx: Long)  = viewModelScope.launch {
        kotlin.runCatching {
            repository.getCatDetail(token, catIdx)
        }.onSuccess {
            _catDetailResponse.value = it
        }.onFailure {
            Log.d("mmm", " get cat detail fail: ${it.message}")
        }
    }
}