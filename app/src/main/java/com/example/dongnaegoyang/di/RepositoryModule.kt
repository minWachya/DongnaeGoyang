package com.example.dongnaegoyang.di

import com.example.dongnaegoyang.data.remote.repository.*
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

    @Binds
    @Singleton
    fun bindsCatRepository(repository: CatRepositoryImpl): CatRepository

    @Binds
    @Singleton
    fun bindsPostRepository(repository: PostRepositoryImpl): PostRepository
}