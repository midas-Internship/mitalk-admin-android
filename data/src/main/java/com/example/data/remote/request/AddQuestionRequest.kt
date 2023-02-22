package com.example.data.remote.request

import com.example.domain.param.AddQuestionParam
import com.google.gson.annotations.SerializedName

data class AddQuestionRequest (
    @SerializedName("question") val question: String,
    @SerializedName("answer") val answer: String
)

fun AddQuestionParam.toRequest() = AddQuestionRequest(
    question = question,
    answer = answer,
)
