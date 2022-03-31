package com.example.dongnaegoyang.home

import com.example.dongnaegoyang.cat_detail.CatDetail

data class CatList  (
    var catPic : CatDetail,
    var catName: String,
    val type: String
) {

    fun toMap() : HashMap<String, Any>{
        val result: HashMap<String, Any> = HashMap()
        result["catPic"] = catPic
        result["catName"] = catName
        return result
    }
}