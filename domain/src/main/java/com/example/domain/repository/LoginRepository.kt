package com.example.domain.repository

import com.example.domain.entity.SignInEntity

interface LoginRepository {
    suspend fun signIn(certificationNumber: String): SignInEntity
}