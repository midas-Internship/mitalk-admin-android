package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(
        certificationNumber: String,
    ) = kotlin.runCatching {
        authRepository.signIn(certificationNumber)
    }
}