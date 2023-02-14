package com.example.domain.usecase

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    
) {

    suspend operator fun invoke(
        certificationNumber: String,
    ) = kotlin.runCatching {

    }
}