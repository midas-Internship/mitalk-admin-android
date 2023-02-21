package com.example.domain.usecase.admin

import com.example.domain.entity.admin.GetQuestionEntity
import com.example.domain.repository.admin.AdminQuestionRepository
import javax.inject.Inject

class GetQuestionListUseCase @Inject constructor(
    private val adminQuestionRepository: AdminQuestionRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        adminQuestionRepository.getQuestionList()
    }
}