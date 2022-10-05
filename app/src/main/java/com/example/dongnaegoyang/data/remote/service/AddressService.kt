package com.example.dongnaegoyang.data.remote.service

import com.example.dongnaegoyang.data.remote.model.response.AddressResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AddressService {
    @GET("v2/local/search/address.json")
    suspend fun getAddressList(@Query("query") query: String,
                               @Query("page") page: Int,
                               @Query("size") size: Int,
                               @Header("Authorization") apikey: String)
    : AddressResponse
}