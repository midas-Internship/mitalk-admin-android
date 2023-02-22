package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.admin.GetUserListUseCase
import com.example.mitalk_admin_android.mvi.admin.AdminIssuedSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminIssuedState
import com.example.mitalk_admin_android.mvi.admin.AdminUserCareSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminUserCareState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminUserCareViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
) : ContainerHost<AdminUserCareState, AdminUserCareSideEffect>, ViewModel() {

    override val container = container<AdminUserCareState, AdminUserCareSideEffect>(AdminUserCareState())

    fun getUserList() = intent {
        viewModelScope.launch {
            getUserListUseCase()
                .onSuccess {
                    reduce { state.copy(userList = it) }
                }
        }
    }

}