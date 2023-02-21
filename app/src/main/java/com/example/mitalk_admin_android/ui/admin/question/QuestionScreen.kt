package com.example.mitalk_admin_android.ui.admin.question

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.ui.admin.header.AdminHeader
import com.example.mitalk_admin_android.ui.util.MiHeader

@Composable
fun QuestionScreen(
    navController: NavController,
) {
    AdminHeader(
        navController = navController,
        title = stringResource(id = R.string.admin_question_title) ,
    )
    
}
