package com.example.domain.usecase.admin

import com.example.domain.entity.admin.GetAllCounsellorEntity
import javax.inject.Inject

class GetCounsellorListUseCase @Inject constructor(

) {
    suspend operator fun invoke() = kotlin.runCatching {
        listOf<GetAllCounsellorEntity>()
    }
}