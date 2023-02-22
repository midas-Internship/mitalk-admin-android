package com.example.data.repository.admin

import com.example.data.remote.datasource.admin.RemoteGetMessageRecordDataSource
import com.example.domain.entity.admin.GetMessageRecordEntity
import com.example.domain.repository.admin.GetMessageRecordRepository
import javax.inject.Inject

class GetMessageRecordRepositoryImpl @Inject constructor(
    private val remoteGetMessageRecordDataSource: RemoteGetMessageRecordDataSource
): GetMessageRecordRepository {
    override suspend fun getMessageRecordList(): List<GetMessageRecordEntity> =
        remoteGetMessageRecordDataSource.getMessageRecordList()
}