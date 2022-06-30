package com.example.dongnaegoyang.model

import com.google.gson.annotations.SerializedName

/*
{
  "photo_count": 4,
  "photo_list": [
      "https://t1.daumcdn.net/cfile/tistory/27738433597DCB1312",
      "https://post-phinf.pstatic.net/MjAyMTAzMzFfMTMg/MDAxNjE3MTgyNDY5OTQ2.7lToiE1uDhnZ60mKOC8ZdK3xe9PVOx2pNdefS_afqdkg.hiWgkZfjEygAmhUuHcpv61eB80v3pOgS8_3ph8bQ_Ywg.JPEG/%EA%BC%AC%EB%A6%AC%EC%95%BC_%281%29.jpg?type=w1200",
      "https://bunny.jjalbot.com/2022/02/d8RfM5c0g.jpeg"
  ],
  "health_count": 2,
  "health_list": {
      "tnr": 0,
      "feed": 0
  },
  "update": "2022-06-30",
  "nickname": "간택받은집사"
}
*/

data class CatDetailInfo(
    @SerializedName("photo_count") val photoCount: Int,
    @SerializedName("photo_list") val photoList: List<String>,
    @SerializedName("health_count") val healthCount: Int,
    @SerializedName("health_list") val healthData: HealthData,
    val update: String,
    val nickname: String
)


data class HealthData(
    val tnr: Int,
    val feed: Int
)
