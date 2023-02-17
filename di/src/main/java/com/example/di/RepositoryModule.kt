package com.example.di

import com.example.data.repository.FileRepositoryImpl
import com.example.data.repository.LoginRepositoryImpl
import com.example.data.repository.RecordRepositoryImpl
import com.example.data.sample.SampleRepositoryImpl
import com.example.domain.repository.FileRepository
import com.example.domain.repository.LoginRepository
import com.example.domain.repository.RecordRepository
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
    abstract fun provideLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    abstract fun provideRecordRepository(
        recordRepositoryImpl: RecordRepositoryImpl
    ): RecordRepository

    @Binds
    abstract fun provideFileRepository(
        fileRepositoryImpl: FileRepositoryImpl
    ): FileRepository
}
