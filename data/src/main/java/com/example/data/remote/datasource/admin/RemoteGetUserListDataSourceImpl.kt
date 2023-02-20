package com.example.data.remote.datasource.admin

import com.example.data.remote.api.AdminApi
import com.example.data.remote.response.toEntity
import com.example.domain.entity.admin.GetAdminUserCareEntity
import javax.inject.Inject

class RemoteGetUserListDataSourceImpl @Inject constructor(
    private val adminApi: AdminApi
): RemoteGetUserListDataSource {
    override suspend fun getUserList(): List<GetAdminUserCareEntity> =
        adminApi.getUserList().map { it.toEntity() }
}