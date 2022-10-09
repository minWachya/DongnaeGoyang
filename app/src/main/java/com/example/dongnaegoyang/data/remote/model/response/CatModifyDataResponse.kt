package com.example.dongnaegoyang.data.remote.model.response

data class CatModifyDataResponse(
    val name: String,
    val appearance: CustomCat,
    val oftenSeen: String,
    val sex: String,
    val age: String,
    val note: String,
    val sido: String,
    val gugun: String,
    val tnr: String?,
    val feed: String?,
    val photoList: List<PhotoList>
)
