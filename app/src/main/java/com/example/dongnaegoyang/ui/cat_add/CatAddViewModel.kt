package com.example.dongnaegoyang.ui.cat_add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongnaegoyang.data.remote.model.BaseResponse
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
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

    private val _response = MutableLiveData<BaseResponse<Int>>()
    val response: LiveData<BaseResponse<Int>> = _response

    fun setArrS3Url(arr: Array<String>) {
        _arrS3Url.value = arr
    }

    fun postCatAdd(token: String, body: CatAddRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repository.postCatAdd(token, body)
        }.onSuccess {
            _response.value = it
        }.onFailure {
            Log.d("mmm", " post cat fail: ${it.message}")
        }
    }

}