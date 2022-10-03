package com.example.dongnaegoyang.di

import com.example.dongnaegoyang.data.remote.service.AddressService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    @Singleton
    fun provideAddressService(retrofit: Retrofit): AddressService =
        retrofit.create(AddressService::class.java)
}