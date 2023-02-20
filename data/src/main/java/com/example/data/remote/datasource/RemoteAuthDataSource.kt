package com.example.data.remote.datasource

import com.example.domain.entity.SignInEntity

interface RemoteAuthDataSource {
    suspend fun signIn(certificationNumber: String): SignInEntity
}