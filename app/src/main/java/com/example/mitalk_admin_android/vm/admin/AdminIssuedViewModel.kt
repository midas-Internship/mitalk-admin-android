package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.admin.AddCounsellorUseCase
import com.example.domain.usecase.admin.GetCounsellorListUseCase
import com.example.mitalk_admin_android.mvi.admin.AdminIssuedSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminIssuedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminIssuedViewModel @Inject constructor(
    private val getCounsellorListUseCase: GetCounsellorListUseCase,
    private val addCounsellorUseCase: AddCounsellorUseCase
): ContainerHost<AdminIssuedState, AdminIssuedSideEffect>, ViewModel() {

    override val container = container<AdminIssuedState, AdminIssuedSideEffect>(AdminIssuedState())

    fun getCounsellorList() = intent {
        viewModelScope.launch {
            getCounsellorListUseCase()
                .onSuccess {
                    reduce { state.copy(counsellorList = it) }
                }
        }
    }

    fun addCounsellor(
        name: String
    ) = intent {
        viewModelScope.launch {
            addCounsellorUseCase(name = name)
                .onSuccess {
                     postSideEffect(AdminIssuedSideEffect.StateRefresh)
                }
        }
    }

    fun deleteCounsellor(key: String) {

    }
}