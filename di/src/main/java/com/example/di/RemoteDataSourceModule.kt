package com.example.di

import com.example.data.remote.datasource.RemoteLoginDataSource
import com.example.data.remote.datasource.RemoteLoginDataSourceImpl
import com.example.data.remote.datasource.RemoteRecordDataSource
import com.example.data.remote.datasource.RemoteRecordDataSourceImpl
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
    abstract fun provideLoginDataSource(
        remoteLoginDataSourceImpl: RemoteLoginDataSourceImpl
    ): RemoteLoginDataSource

    @Singleton
    @Binds
    abstract fun provideRecordDataSource(
        remoteRecordDataSourceImpl: RemoteRecordDataSourceImpl
    ): RemoteRecordDataSource
}