package com.example.data.remote.api

import com.example.data.remote.api.AdminApi.Companion.Admin
import com.example.data.remote.response.GetUserListResponse
import retrofit2.http.GET

interface AdminApi {
    @GET("$Admin/customer")
    suspend fun getUserList(): List<GetUserListResponse>

    private companion object {
        const val Admin = "/admin"
    }
}
