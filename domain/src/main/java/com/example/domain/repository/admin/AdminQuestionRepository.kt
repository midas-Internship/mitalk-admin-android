package com.example.domain.repository.admin

import com.example.domain.entity.admin.GetQuestionEntity
import com.example.domain.param.AddQuestionParam

interface AdminQuestionRepository {
    suspend fun getQuestionList(): List<GetQuestionEntity>

    suspend fun addQuestion(addQuestionParam: AddQuestionParam)
}