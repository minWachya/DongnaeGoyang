package com.example.dongnaegoyang.ui.cat_add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class CatAddViewModel: ViewModel() {
    // s3에서 받아온 url 배열
    private val _arrS3Url = MutableLiveData<Array<String>>()
    val arrS3Url: LiveData<Array<String>> = _arrS3Url

    fun setArrS3Url(list: Array<String>) {
        _arrS3Url.value = list
    }

}