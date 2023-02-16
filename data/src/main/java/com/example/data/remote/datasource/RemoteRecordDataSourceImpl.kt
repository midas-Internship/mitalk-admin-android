package com.example.data.remote.datasource

import com.example.data.remote.api.RecordAPi
import com.example.data.remote.response.RecordResponse
import com.example.data.remote.response.toEntity
import com.example.domain.entity.RecordEntity
import javax.inject.Inject

class RemoteRecordDataSourceImpl @Inject constructor(
    private val recordAPi: RecordAPi,
): RemoteRecordDataSource {
    override suspend fun getRecordList(): RecordEntity =
        recordAPi.getRecordList().toEntity()
}