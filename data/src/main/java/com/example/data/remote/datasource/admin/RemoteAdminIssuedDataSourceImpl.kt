package com.example.data.remote.datasource.admin

import android.util.Log
import com.example.data.remote.api.AdminApi
import com.example.data.remote.request.AddCounsellorRequest
import com.example.data.remote.response.admin.toEntity
import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.admin.GetAllCounsellorEntity
import java.util.*
import javax.inject.Inject

class RemoteAdminIssuedDataSourceImpl @Inject constructor(
    private val adminApi: AdminApi
): RemoteAdminIssuedDataSource {
    override suspend fun getCounsellorList(): List<GetAllCounsellorEntity> = miTalkApiCall {
        adminApi.getCounsellorList().map { it.toEntity() }
    }

    override suspend fun addCounsellor(
        name: String
    ) = miTalkApiCall {
        adminApi.addCounsellor(
            addCounsellorRequest = AddCounsellorRequest(name)
        )
    }

    override suspend fun deleteCounsellor(
        id: String
    ) = miTalkApiCall {
        adminApi.deleteCounsellor(id)
    }
}