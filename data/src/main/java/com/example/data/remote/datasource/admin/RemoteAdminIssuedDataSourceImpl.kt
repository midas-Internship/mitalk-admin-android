package com.example.data.remote.datasource.admin

import com.example.data.remote.api.AdminApi
import com.example.data.remote.response.admin.toEntity
import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.admin.GetAllCounsellorEntity
import javax.inject.Inject

class RemoteAdminIssuedDataSourceImpl @Inject constructor(
    private val adminApi: AdminApi
): RemoteAdminIssuedDataSource {
    override suspend fun getCounsellorList(): List<GetAllCounsellorEntity> = miTalkApiCall {
        adminApi.getCounsellorList().map { it.toEntity() }
    }
}