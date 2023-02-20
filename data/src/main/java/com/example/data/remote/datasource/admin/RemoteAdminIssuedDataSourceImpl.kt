package com.example.data.remote.datasource.admin

import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.admin.GetAllCounsellorEntity
import javax.inject.Inject

class RemoteAdminIssuedDataSourceImpl @Inject constructor(

): RemoteAdminIssuedDataSource {
    override suspend fun getCounsellorList(): List<GetAllCounsellorEntity> = miTalkApiCall {
        listOf()
    }
}