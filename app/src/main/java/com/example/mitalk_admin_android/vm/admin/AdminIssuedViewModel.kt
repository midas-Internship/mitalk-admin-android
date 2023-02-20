package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import com.example.mitalk_admin_android.mvi.admin.AdminIssuedSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminIssuedState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminIssuedViewModel @Inject constructor(

): ContainerHost<AdminIssuedState, AdminIssuedSideEffect>, ViewModel() {

    override val container = container<AdminIssuedState, AdminIssuedSideEffect>(AdminIssuedState())

    fun deleteCounsellor(key: String) {

    }
}