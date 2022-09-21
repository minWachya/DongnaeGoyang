package com.example.dongnaegoyang.ui.cat_detail_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dongnaegoyang.model.CatDetailInfo
import com.example.dongnaegoyang.repository.cat_detail_info.CatDetailInfoRepository

class CatDetailInfoViewModel(private val catDetailInfoRepository: CatDetailInfoRepository): ViewModel() {
    private val _catDetailInfo = MutableLiveData<CatDetailInfo>()
    val catDetailInfo: LiveData<CatDetailInfo> = _catDetailInfo

    init {
        getCatDetailInfo()
    }

    // 데이터 요청
    private fun getCatDetailInfo() {
        val catDetailInfoData = catDetailInfoRepository.getCatDetailInfo()
        catDetailInfoData?.let { catDetailInfo ->
            _catDetailInfo.value = catDetailInfo
        }
    }
}