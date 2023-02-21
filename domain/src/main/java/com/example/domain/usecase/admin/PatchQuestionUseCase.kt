package com.example.domain.usecase.admin

import com.example.domain.param.PatchQuestionParam
import com.example.domain.repository.admin.AdminQuestionRepository
import javax.inject.Inject

class PatchQuestionUseCase @Inject constructor(
    private val adminQuestionRepository: AdminQuestionRepository,
) {
    suspend operator fun invoke(
        patchQuestionParam: PatchQuestionParam,
    ) = kotlin.runCatching {
        adminQuestionRepository.patchQuestion(
            patchQuestionParam = patchQuestionParam,
        )
    }
}
