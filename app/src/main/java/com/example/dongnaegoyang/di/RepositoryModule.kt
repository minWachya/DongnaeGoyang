package com.example.dongnaegoyang.di

import com.example.dongnaegoyang.data.remote.repository.AddressRepository
import com.example.dongnaegoyang.data.remote.repository.AddressRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsAddressRepository(repository: AddressRepositoryImpl): AddressRepository
}