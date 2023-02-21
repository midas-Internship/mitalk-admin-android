package com.example.data.repository.admin

import com.example.domain.entity.admin.GetQuestionEntity
import com.example.domain.repository.admin.AdminQuestionRepository
import javax.inject.Inject

class AdminQuestionRepositoryImpl @Inject constructor(

): AdminQuestionRepository {
    override suspend fun getQuestionList(): List<GetQuestionEntity> =
        listOf()
}