package com.example.data.remote.response.admin

import com.example.domain.entity.admin.GetAllCounsellorEntity
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class GetCounsellorResponse(
    @SerializedName("counsellor_id") val counsellorId: UUID,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
)

fun GetCounsellorResponse.toEntity() = GetAllCounsellorEntity(
    counsellorId = counsellorId.toString(),
    name = name,
    status = status,
)
