package com.example.data.remote.response

import com.example.domain.entity.admin.GetAdminUserCareEntity
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class GetUserListResponse (
    @SerializedName("id") val id: UUID,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("session") val session: String?,
)

fun GetUserListResponse.toEntity() = GetAdminUserCareEntity(
    id = id.toString(),
    name = name,
    email = email,
    session = session,
)
