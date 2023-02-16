package com.example.data.remote.api

import com.example.data.remote.response.RecordResponse
import retrofit2.http.GET

interface RecordAPi {

    @GET("/counsellor/record")
    suspend fun getRecordList(): List<RecordResponse>
}