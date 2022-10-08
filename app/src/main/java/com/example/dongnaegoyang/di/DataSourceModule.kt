package com.example.dongnaegoyang.di

import com.example.dongnaegoyang.data.remote.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindsAddressDataSource(dataSourceImpl: AddressDataSourceImpl): AddressDataSource

    @Binds
    @Singleton
    fun bindsCatDataSource(dataSourceImpl: CatDataSourceImpl): CatDataSource

    @Binds
    @Singleton
    fun bindsPostDataSource(dataSourceImpl: PostDataSourceImpl): PostDataSource
}