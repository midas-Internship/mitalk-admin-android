package com.example.data.remote.datasource.admin

import com.example.data.remote.api.AdminApi
import com.example.data.remote.response.admin.toEntity
import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.admin.GetAdminUserCareEntity
import javax.inject.Inject

class RemoteGetUserListDataSourceImpl @Inject constructor(
    private val adminApi: AdminApi
): RemoteGetUserListDataSource {
    override suspend fun getUserList(): List<GetAdminUserCareEntity> = miTalkApiCall {
        adminApi.getUserList().map { it.toEntity() }
    }
}