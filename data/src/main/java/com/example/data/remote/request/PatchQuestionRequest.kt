package com.example.data.remote.request

import com.example.domain.param.PatchQuestionParam
import com.google.gson.annotations.SerializedName

data class PatchQuestionRequest(
    @SerializedName("question") val question: String,
    @SerializedName("answer") val answer: String,
)

fun PatchQuestionParam.toRequest() = PatchQuestionRequest(
    question = question,
    answer = answer,
)