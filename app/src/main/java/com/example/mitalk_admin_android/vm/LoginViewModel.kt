package com.example.mitalk_admin_android.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.exception.BadRequestException
import com.example.domain.exception.NotFoundException
import com.example.domain.usecase.auth.LoginUseCase
import com.example.mitalk_admin_android.mvi.LoginSideEffect
import com.example.mitalk_admin_android.mvi.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {

    override val container = container<LoginState, LoginSideEffect>(LoginState())

    fun signIn(
        certificationNumber: String,
    ) = intent {

        viewModelScope.launch {
            loginUseCase(
                certificationNumber = certificationNumber
            ).onSuccess {
                postSideEffect(
                    LoginSideEffect.LoginSuccess(
                        role = it,
                        key = certificationNumber,
                    )
                )
            }.onFailure {
                when (it) {
                    is BadRequestException -> Log.d("TAG", "BadRequest")
                    is NotFoundException -> Log.d("TAG", "NotFound")
                    else -> Log.d("TAG", "fail" + it.message)
                }
            }
        }
    }

    fun inputCertificationNumber(certificationNumber: String) = intent {
        reduce { state.copy(certificationNumber = certificationNumber) }
    }
}