package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import com.example.mitalk_admin_android.mvi.admin.AdminStatisticsState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminStatisticsViewModel @Inject constructor(

) : ContainerHost<AdminStatisticsState, Unit>, ViewModel() {

    override val container = container<AdminStatisticsState,Unit>(AdminStatisticsState())
}