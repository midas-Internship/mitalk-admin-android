package com.example.mitalk_admin_android.mvi

data class LoginState(
    val certificationNumber: String = "",
)

sealed class LoginSideEffect {
    data class LoginSuccess(val role: String, val key: String) : LoginSideEffect()
}