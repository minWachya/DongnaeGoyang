package com.example.dongnaegoyang.data.local

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address_name") val addressName: String,
    @SerializedName("address") val addressInfo: AddressInfo,
)

data class AddressInfo(
    @SerializedName("region_1depth_name") val address1: String,
    @SerializedName("region_2depth_name") val address2: String,
    @SerializedName("region_3depth_name") val address3: String
)

