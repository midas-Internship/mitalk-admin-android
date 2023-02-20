package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetAdminUserCareEntity
import javax.inject.Inject

class RemoteGetUserListDataSourceImpl @Inject constructor(
    
): RemoteGetUserListDataSource {
    override suspend fun getUserList(): List<GetAdminUserCareEntity> = listOf()
}