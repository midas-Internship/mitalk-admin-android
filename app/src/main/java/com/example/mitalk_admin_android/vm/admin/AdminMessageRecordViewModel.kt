package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import com.example.mitalk_admin_android.mvi.admin.AdminMessageRecordSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminMessageRecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminMessageRecordViewModel @Inject constructor(

) : ContainerHost<AdminMessageRecordState, AdminMessageRecordSideEffect>, ViewModel() {

    override val container = container<AdminMessageRecordState, AdminMessageRecordSideEffect>(AdminMessageRecordState())

    fun openAdminMessageRecordDetail(id: String) {

    }
}