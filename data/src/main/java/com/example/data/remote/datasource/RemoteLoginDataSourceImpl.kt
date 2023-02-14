package com.example.data.remote.datasource

import com.example.data.remote.api.SignInApi
import com.example.data.remote.response.toEntity
import com.example.domain.entity.SignInEntity
import javax.inject.Inject

class RemoteLoginDataSourceImpl @Inject constructor(
    private val signInApi: SignInApi,
): RemoteLoginDataSource {
    override suspend fun signIn(certificationNumber: String): SignInEntity =
        signInApi.signIn(certificationNumber).toEntity()
}