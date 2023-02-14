package com.example.mitalk_admin_android.vm

import android.content.pm.SigningInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.DialogNavigator
import com.example.domain.usecase.LoginUseCase
import com.example.mitalk_admin_android.mvi.LoginSideEffect
import com.example.mitalk_admin_android.mvi.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel(){

    override val container = container<LoginState, LoginSideEffect>(LoginState())

    fun signIn(
        certificationNumber: String,
    ) = intent {
       viewModelScope.launch {

       }
    }
}