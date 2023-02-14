package com.example.data.remote.response

import com.example.domain.entity.SignInEntity
import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("access_token") val access_token: String,
    @SerializedName("refresh_token") val refresh_token: String,
)

internal fun SignInResponse.toEntity() = SignInEntity(
    access_token = access_token,
    refresh_token = refresh_token,
)
