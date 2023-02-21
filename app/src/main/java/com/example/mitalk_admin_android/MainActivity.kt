package com.example.mitalk_admin_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mitalk_admin_android.ui.admin.main.AdminMainScreen
import com.example.mitalk_admin_android.ui.LoginScreen
import com.example.mitalk_admin_android.ui.admin.issued.AdminIssuedScreen
import com.example.mitalk_admin_android.ui.admin.usercare.AdminUserCareScreen
import com.example.mitalk_admin_android.ui.admin.messagerecord.AdminMessageRecordScreen
import com.example.mitalk_admin_android.ui.admin.question.AdminQuestionScreen
import com.example.mitalk_admin_android.ui.chat.ChatScreen
import com.example.mitalk_admin_android.ui.counsellor.CounsellorMainScreen
import com.example.mitalk_admin_android.ui.record.RecordScreen
import com.example.mitalk_admin_android.util.theme.base.MitalkadminandroidTheme
import com.example.mitalk_admin_android.vm.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MitalkadminandroidTheme {
                val navController = rememberNavController()
                BaseApp(navController)
            }
        }
    }
}

@Composable
fun BaseApp(navController: NavHostController) {
    val viewModel = viewModel<ChatViewModel>()

    NavHost(navController = navController, startDestination = AppNavigationItem.Login.route) {
        composable(AppNavigationItem.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(AppNavigationItem.CounsellorMain.route) {
            CounsellorMainScreen(navController = navController, vm = viewModel)
        }
        composable(
            route = AppNavigationItem.Chat.route
                    + DeepLinkKey.ROOM_ID + "{${DeepLinkKey.ROOM_ID}}",
            arguments = listOf(
                navArgument(DeepLinkKey.ROOM_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val roomId = it.arguments?.getString(DeepLinkKey.ROOM_ID) ?: ""
            ChatScreen(navController = navController, roomId = roomId, vm = viewModel)
        }
        composable(AppNavigationItem.AdminIssued.route) {
            AdminIssuedScreen(navController = navController)
        }
        composable(AppNavigationItem.AdminUserCare.route) {
            AdminUserCareScreen(navController = navController)
        }
        composable(AppNavigationItem.AdminMessageRecord.route) {
            AdminMessageRecordScreen(navController = navController)
        }
        composable(AppNavigationItem.AdminQuestion.route) {
            AdminQuestionScreen(navController = navController)
        }
        composable(
            route = AppNavigationItem.AdminMain.route
                    + DeepLinkKey.KEY + "{${DeepLinkKey.KEY}}",
            arguments = listOf(
                navArgument(DeepLinkKey.KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val key = it.arguments?.getString(DeepLinkKey.KEY) ?: ""
            AdminMainScreen(navController = navController, key)
        }
        composable(AppNavigationItem.Record.route) {
            RecordScreen(navController = navController)
        }
    }
}

sealed class AppNavigationItem(val route: String) {
    object Login : AppNavigationItem("Login")

    object AdminMain : AppNavigationItem("AdminMain")

    object CounsellorMain : AppNavigationItem("CounsellorMain")

    object AdminIssued : AppNavigationItem("AdminIssued")

    object AdminUserCare : AppNavigationItem("AdminUserCare")
    object AdminMessageRecord : AppNavigationItem("AdminMessageRecord")

    object AdminQuestion : AppNavigationItem("AdminQuestion")
    object Chat : AppNavigationItem("Chat")

    object Record : AppNavigationItem("Record")
}

object DeepLinkKey {
    const val KEY = "KEY"
    const val ROOM_ID = "roomId"
}
