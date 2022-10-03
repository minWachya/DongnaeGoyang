package com.example.dongnaegoyang.data.remote.repository

import com.example.dongnaegoyang.data.remote.model.response.AddressResponse

interface AddressRepository {
    suspend fun getAddressList(query: String,
                               page: Int,
                               size: Int,
                               apikey: String): AddressResponse
}