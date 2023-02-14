package com.example.data.remote

import com.example.domain.entity.SignInEntity
import javax.inject.Inject

class RemoteLoginDataSourceImpl @Inject constructor(

): RemoteLoginDataSource {
    override suspend fun signIn(certificationNumber: String): SignInEntity {
        TODO("Not yet implemented")
    }
}