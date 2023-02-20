package com.example.di

import com.example.data.remote.datasource.*
import com.example.data.remote.datasource.admin.*
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

    @Singleton
    @Binds
    abstract fun provideGetUserListDataSource(
        remoteGetUserListDataSourceImpl: RemoteGetUserListDataSourceImpl
    ): RemoteGetUserListDataSource

    @Binds
    abstract fun provideGetMessageRecordDataSource(
        remoteGetMessageRecordDataSourceImpl: RemoteGetMessageRecordDataSourceImpl
    ): RemoteGetMessageRecordDataSource

    @Binds
    abstract fun provideAdminIssuedDataSource(
        remoteAdminIssuedDataSourceImpl: RemoteAdminIssuedDataSourceImpl
    ): RemoteAdminIssuedDataSource
}