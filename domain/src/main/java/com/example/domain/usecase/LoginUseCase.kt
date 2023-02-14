package com.example.domain.usecase

import com.example.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(
        certificationNumber: String,
    ) = kotlin.runCatching {
        loginRepository.signIn(certificationNumber)
    }
}