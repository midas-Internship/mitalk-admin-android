package com.example.data.respositiry

import com.example.data.remote.datasource.RemoteRecordDataSource
import com.example.data.repository.RecordRepositoryImpl
import com.example.domain.entity.RecordEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RecordRepositoryUnitTest {
    private val remoteRecordDataSource = mock<RemoteRecordDataSource>()
    private val recordRepository = RecordRepositoryImpl(remoteRecordDataSource)

    @Test
    fun testGetRecordList() {
        val entity = mock<List<RecordEntity>>()
        runBlocking {
            whenever(remoteRecordDataSource.getRecordList()).thenReturn(entity)
            val result = recordRepository.getRecordList()
            assertEquals(entity, result)
        }
    }
}