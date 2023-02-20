package com.example.domain.entity.admin

data class GetAdminMessageRecord(
    val id: String,
    val startAt: String,
    val counsellorName: String,
    val customerName: String,
    val type: String,
)
