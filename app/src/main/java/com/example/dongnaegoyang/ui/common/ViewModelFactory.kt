package com.example.dongnaegoyang.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dongnaegoyang.AssetLoader
import com.example.dongnaegoyang.repository.cat_detail.CatDetailAssetDataSource
import com.example.dongnaegoyang.repository.cat_detail.CatDetailRepository
import com.example.dongnaegoyang.repository.cat_detail_info.CatDetailInfoAssetDataSource
import com.example.dongnaegoyang.repository.cat_detail_info.CatDetailInfoRepository
import com.example.dongnaegoyang.ui.cat_detail.CatDetailViewModel
import com.example.dongnaegoyang.ui.cat_detail_info.CatDetailInfoViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    // ViewModel 생성하고 반환하기
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 타입 검사
        return when {
            // 고양이 상세
            modelClass.isAssignableFrom(CatDetailViewModel::class.java) -> {
                val repository = CatDetailRepository(CatDetailAssetDataSource(AssetLoader(context)))
                CatDetailViewModel(repository) as T
            }
            // 고양이 상세: 정보
            modelClass.isAssignableFrom(CatDetailInfoViewModel::class.java) -> {
                val repository = CatDetailInfoRepository(CatDetailInfoAssetDataSource(AssetLoader(context)))
                CatDetailInfoViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}