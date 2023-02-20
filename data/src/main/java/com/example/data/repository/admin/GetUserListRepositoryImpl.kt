package com.example.data.repository.admin

import com.example.data.remote.datasource.admin.RemoteGetUserListDataSource
import com.example.domain.entity.admin.GetAdminUserCareEntity
import com.example.domain.repository.admin.GetUserListRepository
import javax.inject.Inject

class GetUserListRepositoryImpl @Inject constructor(
    private val remoteGetUserListDataSource: RemoteGetUserListDataSource,
): GetUserListRepository {
    override suspend fun getUserList(): List<GetAdminUserCareEntity> =
        remoteGetUserListDataSource.getUserList()
}