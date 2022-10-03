package com.example.dongnaegoyang.data.remote.datasource

import com.example.dongnaegoyang.data.remote.model.response.AddressResponse

interface AddressDataSource {
    suspend fun getAddressList(query: String,
                               page: Int,
                               size: Int,
                               apikey: String): AddressResponse
}