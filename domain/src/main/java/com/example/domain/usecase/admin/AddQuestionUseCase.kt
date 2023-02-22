package com.example.domain.usecase.admin

import com.example.domain.param.AddQuestionParam
import com.example.domain.repository.admin.AdminQuestionRepository
import javax.inject.Inject

class AddQuestionUseCase @Inject constructor(
    private val adminQuestionRepository: AdminQuestionRepository
) {
    suspend operator fun invoke(
        addQuestionParam: AddQuestionParam,
    ) = kotlin.runCatching {
        adminQuestionRepository.addQuestion(
            addQuestionParam = addQuestionParam,
        )
    }
}