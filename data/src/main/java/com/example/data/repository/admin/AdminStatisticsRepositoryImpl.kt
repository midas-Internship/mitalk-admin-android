package com.example.data.repository.admin

import com.example.data.remote.datasource.admin.RemoteAdminStatisticsDataSource
import com.example.domain.entity.admin.GetStatisticsEntity
import com.example.domain.entity.admin.StatisticsDetailEntity
import com.example.domain.repository.admin.AdminStatisticsRepository
import javax.inject.Inject

class AdminStatisticsRepositoryImpl @Inject constructor(
    private val remoteAdminStatisticsDataSource: RemoteAdminStatisticsDataSource
) : AdminStatisticsRepository {
    override suspend fun getStatisticsList(): List<GetStatisticsEntity> =
        remoteAdminStatisticsDataSource.getStatisticsList()

    override suspend fun getStatisticsDetail(id: String): StatisticsDetailEntity =
        remoteAdminStatisticsDataSource.getStatisticsDetail(id)
}