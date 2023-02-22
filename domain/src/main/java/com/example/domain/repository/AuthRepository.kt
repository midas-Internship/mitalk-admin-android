package com.example.domain.repository

import com.example.domain.entity.SignInEntity

interface AuthRepository {
    suspend fun signIn(certificationNumber: String): SignInEntity
    suspend fun getAccessToken(): String

    suspend fun autoLogin(): SignInEntity
    suspend fun logout()
}