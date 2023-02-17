package com.example.data.remote.response

import com.example.domain.entity.SignInEntity
import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

data class SignInResponse(
    @SerializedName("access_token") val access_token: String,
    @SerializedName("refresh_token") val refresh_token: String,
    @SerializedName("role") val role: String,
    @SerializedName("access_exp") val access_exp: String,
    @SerializedName("refresh_exp") val refresh_exp: String,
)

internal fun SignInResponse.toEntity() = SignInEntity(
    access_token = access_token,
    refresh_token = refresh_token,
    role = role,
    access_exp = ZonedDateTime.parse(access_exp),
    refresh_exp = ZonedDateTime.parse(refresh_exp)
)
