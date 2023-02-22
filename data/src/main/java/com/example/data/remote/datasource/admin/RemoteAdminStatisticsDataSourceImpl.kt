package com.example.data.remote.datasource.admin

import com.example.data.remote.api.AdminApi
import com.example.data.remote.response.admin.toEntity
import com.example.domain.entity.admin.GetStatisticsEntity
import com.example.domain.entity.admin.StatisticsDetailEntity
import java.util.*
import javax.inject.Inject

class RemoteAdminStatisticsDataSourceImpl @Inject constructor(
    private val adminApi: AdminApi
): RemoteAdminStatisticsDataSource {
    override suspend fun getStatisticsList(): List<GetStatisticsEntity> =
        adminApi.getStatisticsList().counsellorStatistics.map { it.toEntity() }

    override suspend fun getStatisticsDetail(id: String): StatisticsDetailEntity =
        adminApi.getStatisticsDetail(UUID.fromString(id)).toEntity()
}