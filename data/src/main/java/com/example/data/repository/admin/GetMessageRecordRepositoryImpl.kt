package com.example.data.repository.admin

import com.example.domain.entity.admin.GetMessageRecordEntity
import com.example.domain.repository.admin.GetMessageRecordRepository
import javax.inject.Inject

class GetMessageRecordRepositoryImpl @Inject constructor(

): GetMessageRecordRepository {
    override suspend fun getMessageRecordList(): List<GetMessageRecordEntity> = listOf()
}