package com.example.domain.repository.admin

import com.example.domain.entity.admin.GetQuestionEntity

interface AdminQuestionRepository {
    suspend fun getQuestionList(): List<GetQuestionEntity>
}