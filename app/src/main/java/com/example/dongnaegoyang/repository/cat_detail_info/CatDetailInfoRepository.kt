package com.example.dongnaegoyang.repository.cat_detail_info

import com.example.dongnaegoyang.model.CatDetailInfo


class CatDetailInfoRepository(private val assetDataSource: CatDetailInfoAssetDataSource) {

    fun getCatDetailInfo(): CatDetailInfo? {
        return assetDataSource.getCatDetailInfo()
    }
}