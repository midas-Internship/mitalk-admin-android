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
    override suspend fun signIn(certificationNumber: String): SignInEntity {
        val result = remoteAuthDataSource.signIn(certificationNumber)
        localAuthDataSource.saveToken(SignInEntity(
            access_token = result.access_token,
            refresh_token = result.refresh_token,
            access_exp = result.access_exp,
            refresh_exp = result.refresh_exp,
            role = result.role,
            id = ""
        ))
        return result
    }

    override suspend fun getAccessToken(): String =
        localAuthDataSource.fetchToken().access_token

    override suspend fun autoLogin(): SignInEntity {
        val refreshToken = localAuthDataSource.fetchToken().refresh_token
        val token = remoteAuthDataSource.tokenRefresh("Bearer $refreshToken")
        localAuthDataSource.saveToken(token)
        return token
    }

    override suspend fun logout() = localAuthDataSource.clearToken()
}