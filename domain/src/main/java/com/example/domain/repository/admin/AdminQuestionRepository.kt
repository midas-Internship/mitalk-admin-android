package com.example.domain.repository.admin

import com.example.domain.entity.admin.GetQuestionEntity
import com.example.domain.param.AddQuestionParam
import com.example.domain.param.PatchQuestionParam

interface AdminQuestionRepository {
    suspend fun getQuestionList(): List<GetQuestionEntity>

    suspend fun patchQuestion(patchQuestionParam: PatchQuestionParam)
    suspend fun addQuestion(addQuestionParam: AddQuestionParam)
}