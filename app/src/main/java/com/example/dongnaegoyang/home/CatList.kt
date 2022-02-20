package com.example.dongnaegoyang.home

data class CatList  (
    var catPic : Int,
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