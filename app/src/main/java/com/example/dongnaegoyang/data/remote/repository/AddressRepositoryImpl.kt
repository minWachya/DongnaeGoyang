package com.example.dongnaegoyang.data.remote.repository

import com.example.dongnaegoyang.data.remote.datasource.AddressDataSource
import com.example.dongnaegoyang.data.remote.model.response.AddressResponse
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
                            private val dataSource: AddressDataSource
): AddressRepository {
    override suspend fun getAddressList(query: String,
                               page: Int,
                               size: Int,
                               apikey: String): AddressResponse
    = dataSource.getAddressList(query, page, size, apikey)
}