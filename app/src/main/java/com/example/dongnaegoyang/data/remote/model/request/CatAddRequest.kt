package com.example.dongnaegoyang.data.remote.model.request

data class CatAddRequest(
    val name: String,
    val color: Int,
    val size: Int,
    val ear: Int,
    val tail: Int,
    val whisker: Int,
    val oftenSeen: String,
    val sex: String,
    val age: String,
    val note: String,
    val sido: String,
    val gugun: String,
    val tnr: String?,
    val food: String?,
    val photoList: Array<String>
)
