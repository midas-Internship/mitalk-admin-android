package com.example.data.remote.datasource.admin

import com.example.data.remote.api.AdminApi
import com.example.data.remote.response.admin.toEntity
import com.example.domain.entity.admin.GetMessageRecordEntity
import javax.inject.Inject

class RemoteGetMessageRecordDataSourceImpl @Inject constructor(
    private val adminApi: AdminApi
): RemoteGetMessageRecordDataSource {
    override suspend fun getMessageRecordList(): List<GetMessageRecordEntity> =
        adminApi.getRecordList().map { it.toEntity() }
}