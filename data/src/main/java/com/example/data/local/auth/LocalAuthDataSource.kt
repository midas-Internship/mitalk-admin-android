package com.example.data.local.auth

import com.example.domain.entity.TokenEntity

interface LocalAuthDataSource {

    suspend fun fetchToken(): TokenEntity

    suspend fun saveToken(tokenEntity: TokenEntity)

    suspend fun clearToken()
}