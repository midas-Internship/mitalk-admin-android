package com.example.data.local.auth

import com.example.data.local.AuthPreference
import com.example.domain.entity.TokenEntity
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val authPreference: AuthPreference,
) : LocalAuthDataSource {
    override suspend fun fetchToken(): TokenEntity =
        with(authPreference) {
            TokenEntity(
                accessToken = fetchAccessToken(),
                refreshToken = fetchRefreshToken(),
                expirationAt = fetchExpirationAt()
            )
        }


    override suspend fun saveToken(tokenEntity: TokenEntity) =
        with(authPreference) {
            saveAccessToken(tokenEntity.accessToken)
            saveRefreshToken(tokenEntity.refreshToken)
            saveExpirationAt(tokenEntity.expirationAt)
        }


    override suspend fun clearToken() =
        with(authPreference) {
            clearAccessToken()
            clearRefreshToken()
            clearExpirationAt()
        }
}