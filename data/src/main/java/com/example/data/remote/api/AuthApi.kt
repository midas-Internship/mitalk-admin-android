package com.example.data.remote.api

import com.example.data.remote.request.SignInRequest
import com.example.data.remote.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {

    @POST("/auth/signin")
    suspend fun signIn(
        @Body signInRequest: SignInRequest,
    ): SignInResponse

    @PATCH("/auth")
    suspend fun tokenRefresh(
        @Header("Refresh-Token") refreshToken: String
    ): SignInResponse
}