package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import com.example.mitalk_admin_android.mvi.admin.AdminIssuedSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminIssuedState
import com.example.mitalk_admin_android.mvi.admin.AdminUserCareSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminUserCareState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminUserCareViewModel @Inject constructor(

) : ContainerHost<AdminUserCareState, AdminUserCareSideEffect>, ViewModel() {

    override val container = container<AdminUserCareState, AdminUserCareSideEffect>(AdminUserCareState())

}