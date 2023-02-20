package com.example.data.remote.api

import com.example.data.remote.api.AdminApi.Companion.Admin
import com.example.data.remote.request.AddCounsellorRequest
import com.example.data.remote.response.admin.GetCounsellorResponse
import com.example.data.remote.response.admin.GetMessageRecordResponse
import com.example.data.remote.response.admin.GetUserListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AdminApi {
    @GET("$Admin/customer")
    suspend fun getUserList(): List<GetUserListResponse>

    @GET("$Admin/record")
    suspend fun getRecordList(): List<GetMessageRecordResponse>

    @GET("$Admin/counsellor")
    suspend fun getCounsellorList(): List<GetCounsellorResponse>

    @POST("$Admin/counsellor")
    suspend fun addCounsellor(
        @Body addCounsellorRequest: AddCounsellorRequest
    )

    private companion object {
        const val Admin = "/admin"
    }
}
