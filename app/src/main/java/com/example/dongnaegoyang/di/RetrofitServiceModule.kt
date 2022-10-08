package com.example.dongnaegoyang.di

import com.example.dongnaegoyang.data.remote.service.AddressService
import com.example.dongnaegoyang.data.remote.service.CatService
import com.example.dongnaegoyang.data.remote.service.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    @Singleton
    fun provideAddressService(@Named("Address") retrofit: Retrofit): AddressService =
        retrofit.create(AddressService::class.java)

    @Provides
    @Singleton
    fun provideCatService(@Named("Base") retrofit: Retrofit): CatService =
        retrofit.create(CatService::class.java)

    @Provides
    @Singleton
    fun providePostService(@Named("Base") retrofit: Retrofit): PostService =
        retrofit.create(PostService::class.java)
}