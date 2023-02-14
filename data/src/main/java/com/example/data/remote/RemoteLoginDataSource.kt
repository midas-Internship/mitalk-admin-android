package com.example.data.remote

import com.example.domain.entity.SignInEntity

interface RemoteLoginDataSource {
    suspend fun signIn(certificationNumber: String): SignInEntity
}