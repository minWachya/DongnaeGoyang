package com.example.dongnaegoyang.repository.cat_detail_info

import com.example.dongnaegoyang.AssetLoader
import com.example.dongnaegoyang.model.CatDetailInfo
import com.google.gson.Gson

class CatDetailInfoAssetDataSource(private val assetLoader: AssetLoader) : CatDetailInfoDataSource {

    private val gson = Gson()

    override fun getCatDetailInfo(): CatDetailInfo? {
        return assetLoader.getJsonString("cat_detailinfo.json").let { catDetailInfoJsonString ->
            gson.fromJson(catDetailInfoJsonString, CatDetailInfo::class.java)
        }
    }
}