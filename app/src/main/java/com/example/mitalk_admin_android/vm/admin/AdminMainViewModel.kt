package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.mitalk_admin_android.mvi.admin.AdminMainSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminMainViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
) : ContainerHost<Unit, AdminMainSideEffect>, ViewModel() {

    override val container = container<Unit, AdminMainSideEffect>(Unit)

    fun logout() = intent {
        viewModelScope.launch {
            logoutUseCase()
                .onSuccess { postSideEffect(AdminMainSideEffect.LogoutSuccess) }
        }
    }
}