package com.example.data.remote.api

import com.example.data.remote.api.AdminApi.Companion.Admin
import com.example.data.remote.request.AddCounsellorRequest
import com.example.data.remote.response.admin.GetCounsellorResponse
import com.example.data.remote.response.admin.GetMessageRecordResponse
import com.example.data.remote.response.admin.GetQuestionResponse
import com.example.data.remote.response.admin.GetUserListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    @DELETE("$Admin/counsellor/{counsellor_id}")
    suspend fun deleteCounsellor(
        @Path("counsellor_id") id: String
    )

    @GET("$Admin/question")
    suspend fun getQuestionList(): List<GetQuestionResponse>
    private companion object {
        const val Admin = "/admin"
    }
}
