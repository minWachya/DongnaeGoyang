package com.example.dongnaegoyang.model


/*

{
  "name": "나비",
  "isAuthor": true,
  "sex": "암컷",
  "age": "2살 추정",
  "town": "XX구 XX동",
  "appear": "경의선숲길 부산집 앞에서 주로 출몰",
  "note": "부산집 사장님이 가끔 먹이를 챙겨주셔서 그런지 사람을 경계하지 않아요.\n카메라 들이대면 포즈 취해주는 개냥이",
  "koshort": 1,
  "ear": 1,
  "tail": 1,
  "size": 1,
  "whisker": 1
}

*/

data class CatDetail(
    val name: String,
    val isAuthor: Boolean,
    val sex: String,
    val age: String,
    val town: String,
    val appear: String,
    val note: String,
    val koshort: Int,
    val ear: Int,
    val tail: Int,
    val size: Int,
    val whisker: Int
)