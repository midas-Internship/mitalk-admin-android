package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetMessageRecordEntity

interface RemoteGetMessageRecordDataSource {
    suspend fun getMessageRecordList(): List<GetMessageRecordEntity>
}