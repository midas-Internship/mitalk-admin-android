package com.example.data.remote.datasource

import com.example.data.remote.api.AuthApi
import com.example.data.remote.request.SignInRequest
import com.example.data.remote.response.toEntity
import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.SignInEntity
import java.util.UUID
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi,
): RemoteAuthDataSource {
    override suspend fun signIn(
        certificationNumber: String
    ): SignInEntity = miTalkApiCall {
        val id = UUID.fromString(certificationNumber)
        authApi.signIn(signInRequest = SignInRequest(id = id)).toEntity()
    }

    override suspend fun tokenRefresh(
        refreshToken: String
    ): SignInEntity = miTalkApiCall {
        authApi.tokenRefresh(refreshToken).toEntity()
    }
}