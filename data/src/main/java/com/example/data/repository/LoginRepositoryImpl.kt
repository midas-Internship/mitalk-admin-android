package com.example.data.repository

import com.example.data.remote.RemoteLoginDataSource
import com.example.domain.entity.SignInEntity
import com.example.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource
): LoginRepository {
    override suspend fun signIn(certificationNumber: String): SignInEntity {
        TODO("Not yet implemented")
    }
}