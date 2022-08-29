package com.example.dongnaegoyang.repository.cat_detail

import com.example.dongnaegoyang.model.CatDetail

interface CatDetailDataSource {
    fun getCatDetail(): CatDetail?
}