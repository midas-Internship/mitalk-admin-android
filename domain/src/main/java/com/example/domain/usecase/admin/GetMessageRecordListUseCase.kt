package com.example.domain.usecase.admin

import com.example.domain.entity.admin.GetMessageRecordEntity
import com.example.domain.repository.admin.GetMessageRecordRepository
import javax.inject.Inject

class GetMessageRecordListUseCase @Inject constructor(
    private val getMessageRecordRepository: GetMessageRecordRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        getMessageRecordRepository.getMessageRecordList()
    }
}