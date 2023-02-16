package com.example.data.remote.datasource

import android.util.Log
import com.example.data.remote.api.SignInApi
import com.example.data.remote.request.SignInRequest
import com.example.data.remote.response.toEntity
import com.example.data.remote.util.getErrorMessage
import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.SignInEntity
import com.example.domain.exception.BadRequestException
import java.util.UUID
import javax.inject.Inject

class RemoteLoginDataSourceImpl @Inject constructor(
    private val signInApi: SignInApi,
): RemoteLoginDataSource {
    override suspend fun signIn(
        certificationNumber: String
    ): SignInEntity = miTalkApiCall {
        val id = UUID.fromString(certificationNumber)
        signInApi.signIn(signInRequest = SignInRequest(id = id)).toEntity()
    }
}