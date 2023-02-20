package com.example.domain.entity

import java.time.ZonedDateTime

data class TokenEntity(
    val accessToken: String,
    val refreshToken: String,
    val expirationAt: ZonedDateTime
)
