package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import com.example.mitalk_admin_android.mvi.admin.AdminQuestionSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminQuestionState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminQuestionViewModel @Inject constructor(

) : ContainerHost<AdminQuestionState, AdminQuestionSideEffect>, ViewModel() {

    override val container =
        container<AdminQuestionState, AdminQuestionSideEffect>(AdminQuestionState())

    fun getAdminQuestionList() {

    }
}