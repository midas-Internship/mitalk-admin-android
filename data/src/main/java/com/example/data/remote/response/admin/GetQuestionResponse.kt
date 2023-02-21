package com.example.data.remote.response.admin

import com.example.domain.entity.admin.GetQuestionEntity
import com.google.gson.annotations.SerializedName

data class GetQuestionResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("question") val question: String,
    @SerializedName("answer") val answer: String,
)

fun GetQuestionResponse.toEntity() = GetQuestionEntity(
    id = id,
    question = question,
    answer = answer
)
