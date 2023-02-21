package com.example.data.repository.admin

import com.example.data.remote.datasource.admin.RemoteAdminQuestionDataSource
import com.example.domain.entity.admin.GetQuestionEntity
import com.example.domain.param.AddQuestionParam
import com.example.domain.repository.admin.AdminQuestionRepository
import javax.inject.Inject

class AdminQuestionRepositoryImpl @Inject constructor(
    private val remoteAdminQuestionDataSource: RemoteAdminQuestionDataSource,
): AdminQuestionRepository {
    override suspend fun getQuestionList(): List<GetQuestionEntity> =
        remoteAdminQuestionDataSource.getQuestionList()

    override suspend fun addQuestion(addQuestionParam: AddQuestionParam) =
        remoteAdminQuestionDataSource.addQuestion(addQuestionParam)
}