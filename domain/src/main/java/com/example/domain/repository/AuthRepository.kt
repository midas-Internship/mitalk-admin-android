package com.example.domain.repository

interface AuthRepository {
    suspend fun signIn(certificationNumber: String): String
    suspend fun getAccessToken(): String

    suspend fun logout()
}