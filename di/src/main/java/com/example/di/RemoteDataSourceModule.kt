package com.example.di

import com.example.data.remote.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun provideAuthDataSource(
        remoteLoginDataSourceImpl: RemoteAuthDataSourceImpl
    ): RemoteAuthDataSource

    @Singleton
    @Binds
    abstract fun provideRecordDataSource(
        remoteRecordDataSourceImpl: RemoteRecordDataSourceImpl
    ): RemoteRecordDataSource

    @Singleton
    @Binds
    abstract fun provideFileDataSource(
        remoteFileDataSourceImpl: RemoteFileDataSourceImpl
    ): RemoteFileDataSource
}