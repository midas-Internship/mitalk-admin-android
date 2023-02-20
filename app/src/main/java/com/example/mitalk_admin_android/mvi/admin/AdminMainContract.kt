package com.example.mitalk_admin_android.mvi.admin

sealed class AdminMainSideEffect {
    object LogoutSuccess : AdminMainSideEffect()
}