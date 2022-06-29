package com.example.dongnaegoyang.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dongnaegoyang.AssetLoader
import com.example.dongnaegoyang.repository.cat_detail.CatDetailAssetDataSource
import com.example.dongnaegoyang.repository.cat_detail.CatDetailRepository
import com.example.dongnaegoyang.ui.cat_detail.CatDetailViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    // ViewModel 생성하고 반환하기
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 타입 검사
        return when {
            modelClass.isAssignableFrom(CatDetailViewModel::class.java) -> {
                // 의존성 관리: 'Hilt' 라이브러리 사용할 수도 있음
                val repository = CatDetailRepository(CatDetailAssetDataSource(AssetLoader(context)))
                CatDetailViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}