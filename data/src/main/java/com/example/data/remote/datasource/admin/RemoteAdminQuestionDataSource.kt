package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetQuestionEntity
import com.example.domain.param.AddQuestionParam
import com.example.domain.param.PatchQuestionParam

interface RemoteAdminQuestionDataSource {
    suspend fun getQuestionList(): List<GetQuestionEntity>

    suspend fun patchQuestion(patchQuestionParam: PatchQuestionParam)

    suspend fun addQuestion(addQuestionParam: AddQuestionParam)
}