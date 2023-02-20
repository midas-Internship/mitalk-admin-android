package com.example.data.remote.api

import com.example.data.remote.response.admin.GetMessageRecordResponse
import com.example.data.remote.response.admin.GetUserListResponse
import com.example.domain.entity.admin.GetMessageRecordEntity
import retrofit2.http.GET

interface AdminApi {
    @GET("$Admin/customer")
    suspend fun getUserList(): List<GetUserListResponse>

    @GET("$Admin/record")
    suspend fun getRecordList(): List<GetMessageRecordResponse>

    private companion object {
        const val Admin = "/admin"
    }
}
