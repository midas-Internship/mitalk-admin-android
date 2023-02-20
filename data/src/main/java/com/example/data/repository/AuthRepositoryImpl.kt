package com.example.data.repository

import com.example.data.local.auth.LocalAuthDataSource
import com.example.data.remote.datasource.RemoteAuthDataSource
import com.example.domain.entity.SignInEntity
import com.example.domain.entity.TokenEntity
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteAuthDataSource: RemoteAuthDataSource,
    private val localAuthDataSource: LocalAuthDataSource
) : AuthRepository {
    override suspend fun signIn(certificationNumber: String): String {
        val result = remoteAuthDataSource.signIn(certificationNumber)
        localAuthDataSource.saveToken(TokenEntity(
            accessToken = result.access_token,
            refreshToken = result.refresh_token,
            expirationAt = result.access_exp
        ))
        return result.role
    }

    override suspend fun getAccessToken(): String =
        localAuthDataSource.fetchToken().accessToken

    override suspend fun logout() = localAuthDataSource.clearToken()
}