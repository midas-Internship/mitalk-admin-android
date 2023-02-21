package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetQuestionEntity
import com.example.domain.param.AddQuestionParam

interface RemoteAdminQuestionDataSource {
    suspend fun getQuestionList(): List<GetQuestionEntity>

    suspend fun addQuestion(addQuestionParam: AddQuestionParam)
}