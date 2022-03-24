package com.example.dongnaegoyang.address_search

data class ResultSearchKeyword(
    var documents: List<Place>          // 검색 결과
)

data class Place(
    var address_name: String,
    var address: Address
)

data class Address (
    var region_1depth_name: String,       // 시도 단위
    var region_2depth_name: String,      // 구 단위
    var region_3depth_name: String      // 동 단위 주소
)