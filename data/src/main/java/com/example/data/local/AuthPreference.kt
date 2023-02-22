package com.example.data.local

import java.time.ZonedDateTime

interface AuthPreference {
    suspend fun saveAccessToken(accessToken: String)

    suspend fun fetchAccessToken(): String

    suspend fun clearAccessToken()

    suspend fun saveRefreshToken(refreshToken: String)

    suspend fun fetchRefreshToken(): String

    suspend fun clearRefreshToken()

    suspend fun saveExpirationAt(expiredAt: ZonedDateTime)

    suspend fun fetchExpirationAt(): ZonedDateTime

    suspend fun clearExpirationAt()

    suspend fun saveRole(role: String)

    suspend fun fetchRole(): String

    suspend fun clearRole()
}