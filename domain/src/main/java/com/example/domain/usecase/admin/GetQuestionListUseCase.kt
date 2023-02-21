package com.example.domain.usecase.admin

import com.example.domain.entity.admin.GetQuestionEntity
import javax.inject.Inject

class GetQuestionListUseCase @Inject constructor(

) {
    suspend operator fun invoke() = kotlin.runCatching {
        listOf<GetQuestionEntity>()
    }
}