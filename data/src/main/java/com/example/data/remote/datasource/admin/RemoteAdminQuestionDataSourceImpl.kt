package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetQuestionEntity
import javax.inject.Inject

class RemoteAdminQuestionDataSourceImpl @Inject constructor(

): RemoteAdminQuestionDataSource {
    override suspend fun getQuestionList(): List<GetQuestionEntity> = listOf()
}