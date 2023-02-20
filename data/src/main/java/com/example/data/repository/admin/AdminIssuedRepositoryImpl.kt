package com.example.data.repository.admin

import com.example.data.remote.datasource.admin.RemoteAdminIssuedDataSource
import com.example.domain.entity.admin.GetAllCounsellorEntity
import com.example.domain.repository.admin.AdminIssuedRepository
import javax.inject.Inject

class AdminIssuedRepositoryImpl @Inject constructor(
    private val remoteAdminIssuedDataSource: RemoteAdminIssuedDataSource
): AdminIssuedRepository {
    override suspend fun getCounsellorList(): List<GetAllCounsellorEntity> =
        remoteAdminIssuedDataSource.getCounsellorList()

    override suspend fun addCounsellor(name: String) =
        remoteAdminIssuedDataSource.addCounsellor(name)
}