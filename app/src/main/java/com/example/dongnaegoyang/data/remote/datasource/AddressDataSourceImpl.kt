package com.example.dongnaegoyang.data.remote.datasource

import com.example.dongnaegoyang.data.remote.model.response.AddressResponse
import com.example.dongnaegoyang.data.remote.service.AddressService
import javax.inject.Inject

class AddressDataSourceImpl @Inject constructor(private val addressService: AddressService): AddressDataSource {
    override suspend fun getAddressList(
        query: String,
        page: Int,
        size: Int,
        apikey: String
    ): AddressResponse = addressService.getAddressList(query, page, size, apikey)

}