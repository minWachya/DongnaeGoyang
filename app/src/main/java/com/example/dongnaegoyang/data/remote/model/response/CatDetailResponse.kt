package com.example.dongnaegoyang.data.remote.model.response

import java.util.*

/*
{
        "catIdx": 3,
        "isWriter": true,
        "name": "야용1",
        "sex": "1",
        "age": "2살",
        "place": "경기 용인시 수지구 상현동",
        "oftenSeen": "45단지 놀이터",
        "note": "사람 좋아함",
        "appearance": {
            "size": 1,
            "ear": 1,
            "color": 1,
            "tail": 1,
            "whisker": 1
        }
    }
*/

data class CatDetailResponse(
    val catIdx: Int,
    val isWriter: Boolean,
    val name: String,
    val sex: String,
    val age: String,
    val place: String,
    val oftenSeen: String,
    val note: String,
    val appearance: CustomCat
)

data class CustomCat(
    val catIdx: Int,
    val size: Int,
    val ear: Int,
    val color: Int,
    val tail: Int,
    val whisker: Int
)