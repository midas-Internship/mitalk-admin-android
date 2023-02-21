package com.example.data.remote.datasource.admin

import android.util.Log
import com.example.data.remote.api.AdminApi
import com.example.data.remote.request.PatchQuestionRequest
import com.example.data.remote.request.toRequest
import com.example.data.remote.response.admin.toEntity
import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.admin.GetQuestionEntity
import com.example.domain.param.AddQuestionParam
import com.example.domain.param.PatchQuestionParam
import javax.inject.Inject

class RemoteAdminQuestionDataSourceImpl @Inject constructor(
    private val adminApi: AdminApi
): RemoteAdminQuestionDataSource {
    override suspend fun getQuestionList(): List<GetQuestionEntity> = miTalkApiCall {
        adminApi.getQuestionList().map { it.toEntity() }
    }

    override suspend fun patchQuestion(patchQuestionParam: PatchQuestionParam) = miTalkApiCall {
        adminApi.patchQuestion(
            id = patchQuestionParam.id,
            patchQuestionRequest = patchQuestionParam.toRequest()
        )
    }

    override suspend fun addQuestion(addQuestionParam: AddQuestionParam) = miTalkApiCall {
        Log.d("TAG", "addQuestionParam: ${addQuestionParam.toRequest()}")
        adminApi.addQuestion(addQuestionParam.toRequest())
    }
}