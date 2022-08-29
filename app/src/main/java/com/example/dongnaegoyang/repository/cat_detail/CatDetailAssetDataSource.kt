package com.example.dongnaegoyang.repository.cat_detail

import com.example.dongnaegoyang.AssetLoader
import com.example.dongnaegoyang.model.CatDetail
import com.google.gson.Gson

class CatDetailAssetDataSource(private val assetLoader: AssetLoader) : CatDetailDataSource {

    private val gson = Gson()

    override fun getCatDetail(): CatDetail? {
        return assetLoader.getJsonString("cat_detail.json").let { catDetailJsonString ->
            gson.fromJson(catDetailJsonString, CatDetail::class.java)
        }
    }
}