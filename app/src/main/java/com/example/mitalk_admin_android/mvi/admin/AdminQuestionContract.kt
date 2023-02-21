package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetQuestionEntity

data class AdminQuestionState(
    val questionList: List<GetQuestionEntity> = listOf()
)

sealed class AdminQuestionSideEffect {
    object ListChanged : AdminQuestionSideEffect()
}