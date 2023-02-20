package com.example.domain.repository.admin

import com.example.domain.entity.admin.GetMessageRecordEntity

interface GetMessageRecordRepository {
    suspend fun getMessageRecordList(): List<GetMessageRecordEntity>
}