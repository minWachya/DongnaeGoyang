package com.example.dongnaegoyang.home

import androidx.annotation.DrawableRes

data class CatList  (
    @DrawableRes
    var catPic : Int,
    var catName: String
) {

    fun toMap() : HashMap<String, Any>{
        val result: HashMap<String, Any> = HashMap()
        result["catPic"] = catPic
        result["catName"] = catName
        return result
    }
}