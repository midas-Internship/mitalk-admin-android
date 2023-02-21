package com.example.data.local.auth

import com.example.data.local.AuthPreference
import com.example.domain.entity.SignInEntity
import com.example.domain.entity.TokenEntity
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val authPreference: AuthPreference,
) : LocalAuthDataSource {
    override suspend fun fetchToken(): SignInEntity =
        with(authPreference) {
            SignInEntity(
                access_token = fetchAccessToken(),
                refresh_token = fetchRefreshToken(),
                access_exp = fetchExpirationAt(),
                refresh_exp = fetchExpirationAt(),
                role = fetchRole(),
                id = ""
            )
        }


    override suspend fun saveToken(signInEntity: SignInEntity) =
        with(authPreference) {
            saveAccessToken(signInEntity.access_token)
            saveRefreshToken(signInEntity.refresh_token)
            saveExpirationAt(signInEntity.refresh_exp)
            saveRole(signInEntity.role)
        }


    override suspend fun clearToken() =
        with(authPreference) {
            clearAccessToken()
            clearRefreshToken()
            clearExpirationAt()
            clearRole()
        }
}