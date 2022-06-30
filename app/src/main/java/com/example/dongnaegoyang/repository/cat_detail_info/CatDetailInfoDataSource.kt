package com.example.dongnaegoyang.repository.cat_detail_info

import com.example.dongnaegoyang.model.CatDetailInfo

interface CatDetailInfoDataSource {
    fun getCatDetailInfo(): CatDetailInfo?
}