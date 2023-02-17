package com.example.data.remote.api

import com.example.data.remote.request.SignInRequest
import com.example.data.remote.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface SignInApi {

    @POST("/auth/signin")
    suspend fun signIn(
        @Body signInRequest: SignInRequest,
    ): SignInResponse
}