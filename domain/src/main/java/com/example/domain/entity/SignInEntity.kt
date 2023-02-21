package com.example.domain.entity

import java.time.ZonedDateTime

data class SignInEntity(
    val access_token: String,
    val refresh_token: String,
    val role: String,
    val access_exp: ZonedDateTime,
    val refresh_exp: ZonedDateTime,
    val id: String
)
