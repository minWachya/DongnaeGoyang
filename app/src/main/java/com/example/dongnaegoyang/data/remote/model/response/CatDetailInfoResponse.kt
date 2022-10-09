package com.example.dongnaegoyang.data.remote.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/*
{
        "catIdx": 3,
        "photoList": [
            {
                "imageIdx": 1,
                "url": "http어쩌구"
            },
            {
                "imageIdx": 2,
                "url": "http어쩌구2"
            },
            {
                "imageIdx": 3,
                "url": "http어쩌구3"
            }
        ],
        "healthInfoCount": 2,
        "tnr": "X",
        "feed": "편식 안 함",
        "modifiedTime": "2022-10-03T15:38:24.390774",
        "writer": {
            "kakaoId": 2154390251,
            "nickname": "박지혜"
        },
        "otherCatList": [
            {
                "catIdx": 2,
                "size": 1,
                "ear": 1,
                "color": 1,
                "tail": 1,
                "whisker": 1
            }
        ]
    }
*/

data class CatDetailInfoResponse(
    val photoList: List<PhotoList>,
    val healthInfoCount: Int,
    val tnr: String,
    val feed: String,
    val modifiedTime: String,
    val writer: User,
    val otherCatList: List<CustomCat>
)

data class PhotoList(
    @SerializedName("imageIdx") val imageIdx: Int,
    @SerializedName("url") val url: String
): Serializable

data class User(
    val kakaoId: Long,
    val nickname: String
)