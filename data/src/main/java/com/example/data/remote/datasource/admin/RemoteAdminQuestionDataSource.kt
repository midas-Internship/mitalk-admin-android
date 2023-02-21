package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetQuestionEntity

interface RemoteAdminQuestionDataSource {
    suspend fun getQuestionList(): List<GetQuestionEntity>
}