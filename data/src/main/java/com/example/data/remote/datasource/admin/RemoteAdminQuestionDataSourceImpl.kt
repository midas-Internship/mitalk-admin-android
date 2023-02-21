package com.example.data.remote.datasource.admin

import com.example.data.remote.api.AdminApi
import com.example.data.remote.request.toRequest
import com.example.data.remote.response.admin.toEntity
import com.example.domain.entity.admin.GetQuestionEntity
import com.example.domain.param.AddQuestionParam
import javax.inject.Inject

class RemoteAdminQuestionDataSourceImpl @Inject constructor(
    private val adminApi: AdminApi
): RemoteAdminQuestionDataSource {
    override suspend fun getQuestionList(): List<GetQuestionEntity> =
        adminApi.getQuestionList().map { it.toEntity() }

    override suspend fun addQuestion(addQuestionParam: AddQuestionParam) =
        adminApi.addQuestion(addQuestionParam.toRequest())
}