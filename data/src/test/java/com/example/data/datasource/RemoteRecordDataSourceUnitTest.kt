package com.example.data.datasource

import com.example.data.remote.api.RecordAPi
import com.example.data.remote.datasource.RemoteRecordDataSource
import com.example.data.remote.datasource.RemoteRecordDataSourceImpl
import com.example.data.remote.response.RecordResponse
import com.example.data.remote.response.toEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.List

class RemoteRecordDataSourceUnitTest {
    private val recordAPi = mock<RecordAPi>()
    private val remoteRecordDataSource: RemoteRecordDataSource =
        RemoteRecordDataSourceImpl(recordAPi)

    @Test
    fun testGetRecordList() {
        val response = listOf(
            RecordResponse(
                type = "",
                name = "",
                time = ZonedDateTime.parse("2023-02-23T09:45:31.7105597+09:00"),
                roomId = UUID.fromString("123e4567-e89b-12d3-a456-556642440000")
            )
        )

        runBlocking {
            whenever(recordAPi.getRecordList()).thenReturn(response)
            val result = remoteRecordDataSource.getRecordList()
            assertEquals(response.map { it.toEntity() }, result)
        }
    }
}