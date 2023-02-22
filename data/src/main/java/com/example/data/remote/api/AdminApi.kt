package com.example.data.remote.api

import com.example.data.remote.api.AdminApi.Companion.Admin
import com.example.data.remote.request.AddCounsellorRequest
import com.example.data.remote.request.AddQuestionRequest
import com.example.data.remote.request.PatchQuestionRequest
import com.example.data.remote.response.admin.*
import com.example.domain.param.AddQuestionParam
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

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

    @POST("$Admin/question")
    suspend fun addQuestion(
        @Body addQuestionRequest: AddQuestionRequest
    )

    @PATCH("$Admin/question/{question-id}")
    suspend fun patchQuestion(
        @Path("question-id") id: Long,
        @Body patchQuestionRequest: PatchQuestionRequest
    )

    @GET("$Admin/statistics")
    suspend fun getStatisticsList(): GetStatisticsResponse

    @GET("$Admin/statistics/{counsellor-id}")
    suspend fun getStatisticsDetail(
        @Path("counsellor-id") id: UUID
    ): StatisticsDetailResponse

    private companion object {
        const val Admin = "/admin"
    }
}
