package com.example.mitalk_admin_android.mvi

data class LoginState(
    val certificationNumber: String = "",
    val role: String = "",
)

sealed class LoginSideEffect {
}