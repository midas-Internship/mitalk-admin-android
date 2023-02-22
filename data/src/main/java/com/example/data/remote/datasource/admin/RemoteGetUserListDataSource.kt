package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetAdminUserCareEntity

interface RemoteGetUserListDataSource {
    suspend fun getUserList(): List<GetAdminUserCareEntity>
}