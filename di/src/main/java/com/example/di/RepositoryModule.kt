package com.example.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.FileRepositoryImpl
import com.example.data.repository.RecordRepositoryImpl
import com.example.data.repository.admin.AdminIssuedRepositoryImpl
import com.example.data.repository.admin.AdminQuestionRepositoryImpl
import com.example.data.repository.admin.GetMessageRecordRepositoryImpl
import com.example.data.repository.admin.GetUserListRepositoryImpl
import com.example.data.sample.SampleRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.FileRepository
import com.example.domain.repository.RecordRepository
import com.example.domain.repository.admin.AdminIssuedRepository
import com.example.domain.repository.admin.AdminQuestionRepository
import com.example.domain.repository.admin.GetMessageRecordRepository
import com.example.domain.repository.admin.GetUserListRepository
import com.example.domain.sample.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideSampleRepository(
        sampleRepositoryImpl: SampleRepositoryImpl,
    ): SampleRepository

    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun provideRecordRepository(
        recordRepositoryImpl: RecordRepositoryImpl
    ): RecordRepository

    @Binds
    abstract fun provideFileRepository(
        fileRepositoryImpl: FileRepositoryImpl
    ): FileRepository

    @Binds
    abstract fun provideGetUserListRepository(
        getUserListRepositoryImpl: GetUserListRepositoryImpl
    ): GetUserListRepository

    @Binds
    abstract fun provideGetMessageRecordRepository(
        getMessageRecordRepositoryImpl: GetMessageRecordRepositoryImpl
    ): GetMessageRecordRepository

    @Binds
    abstract fun provideAdminIssuedRepository(
        adminIssuedRepositoryImpl: AdminIssuedRepositoryImpl
    ): AdminIssuedRepository

    @Binds
    abstract fun provideAdminQuestionRepository(
        adminQuestionRepositoryImpl: AdminQuestionRepositoryImpl
    ): AdminQuestionRepository
}
