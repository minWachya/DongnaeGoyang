package com.example.dongnaegoyang.ui.cat_add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.request.CatModifyRequest
import com.example.dongnaegoyang.data.remote.model.response.CatModifyDataResponse
import com.example.dongnaegoyang.data.remote.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatAddViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {
    // s3에서 받아온 url 배열
    private val _arrS3Url = MutableLiveData<Array<String>>()
    val arrS3Url: LiveData<Array<String>> = _arrS3Url

    private val _catAddResponse = MutableLiveData<BaseResponse<Int>>()
    val catAddResponse: LiveData<BaseResponse<Int>> = _catAddResponse

    private val _catModifyDataResponse = MutableLiveData<BaseResponse<CatModifyDataResponse>>()
    val catModifyDataResponse: LiveData<BaseResponse<CatModifyDataResponse>> = _catModifyDataResponse

    private val _catModifyResponse = MutableLiveData<BaseResponse<Int>>()
    val catModifyResponse: LiveData<BaseResponse<Int>> = _catModifyResponse

    fun setArrS3Url(arr: Array<String>) {
        _arrS3Url.value = arr
    }

    fun postCatAdd(token: String, body: CatAddRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repository.postCatAdd(token, body)
        }.onSuccess {
            _catAddResponse.value = it
        }.onFailure {
            Log.d("mmm", " post cat add fail: ${it.message}")
        }
    }

    fun getCatModifyData(token: String, catIdx: Long) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getCatModifyData(token, catIdx)
        }.onSuccess {
            _catModifyDataResponse.value = it
            Log.d("mmm modify data", it.toString())
        }.onFailure {
            Log.d("mmm", " get cat modify data fail: ${it.message}")
        }
    }

    fun patchCatModify(token: String, catIdx: Long, body: CatModifyRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repository.patchCatModify(token, catIdx, body)
        }.onSuccess {
            _catModifyResponse.value = it
        }.onFailure {
            Log.d("mmm", " patch cat modify fail: ${it.message}")
        }
    }

}