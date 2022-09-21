package com.example.dongnaegoyang.repository.cat_detail

import com.example.dongnaegoyang.model.CatDetail

class CatDetailRepository(private val assetDataSource: CatDetailAssetDataSource) {

    fun getCatDetail(): CatDetail? {
        return assetDataSource.getCatDetail()
    }
}