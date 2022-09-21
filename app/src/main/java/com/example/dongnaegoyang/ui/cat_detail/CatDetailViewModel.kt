package com.example.dongnaegoyang.ui.cat_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dongnaegoyang.model.CatDetail
import com.example.dongnaegoyang.repository.cat_detail.CatDetailRepository

class CatDetailViewModel(private val catDetailRepository: CatDetailRepository): ViewModel() {
    // 외부 접근 불가 변수: _로 시작
    private val _catDetail = MutableLiveData<CatDetail>()
    val catDetail: LiveData<CatDetail> = _catDetail

    init {
        getCatDetail()
    }

    // 데이터 요청
    private fun getCatDetail() {
        val catDetailData = catDetailRepository.getCatDetail()
        catDetailData?.let { catDetail ->
            _catDetail.value = catDetail
        }
    }
}