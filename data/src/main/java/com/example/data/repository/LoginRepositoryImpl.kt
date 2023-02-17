package com.example.data.repository

import android.util.Log
import com.example.data.remote.datasource.RemoteLoginDataSource
import com.example.domain.entity.SignInEntity
import com.example.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource
): LoginRepository {
    override suspend fun signIn(certificationNumber: String): SignInEntity  =
        remoteLoginDataSource.signIn(certificationNumber)
}