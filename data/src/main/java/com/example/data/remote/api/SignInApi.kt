package com.example.data.remote.api

import com.example.data.remote.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface SignInApi {

    @GET("signIn")
    suspend fun signIn(
        @Query("certification-number") certificationNumber: String,
    ): SignInResponse
}