package com.example.data.local.auth

import com.example.domain.entity.SignInEntity
import com.example.domain.entity.TokenEntity

interface LocalAuthDataSource {

    suspend fun fetchToken(): SignInEntity

    suspend fun saveToken(signInEntity: SignInEntity)

    suspend fun clearToken()
}