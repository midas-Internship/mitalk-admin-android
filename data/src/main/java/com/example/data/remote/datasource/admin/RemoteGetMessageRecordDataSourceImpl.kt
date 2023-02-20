package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetMessageRecordEntity
import javax.inject.Inject

class RemoteGetMessageRecordDataSourceImpl @Inject constructor(

): RemoteGetMessageRecordDataSource {
    override suspend fun getMessageRecordList(): List<GetMessageRecordEntity> = listOf()
}