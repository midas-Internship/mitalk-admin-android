package com.example.mitalk_admin_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.example.mitalk_admin_android.ui.counsellor.chat.ChatScreen
import com.example.mitalk_admin_android.ui.admin.question.AdminQuestionScreen
import com.example.mitalk_admin_android.ui.admin.statistics.AdminStatisticsScreen
import com.example.mitalk_admin_android.ui.counsellor.CounsellorMainScreen
import com.example.mitalk_admin_android.ui.record.RecordScreen
import com.example.mitalk_admin_android.ui.record.RecordDetailScreen
import com.example.mitalk_admin_android.util.MiTalkExceptionHandler
import com.example.mitalk_admin_android.util.theme.base.MitalkadminandroidTheme
import com.example.mitalk_admin_android.vm.ChatViewModel
import com.example.mitalk_admin_android.vm.TokenRefreshViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tokenRefreshViewModel: TokenRefreshViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MitalkadminandroidTheme {
                val navController = rememberNavController()
                BaseApp(navController)

                Thread.setDefaultUncaughtExceptionHandler(
                    MiTalkExceptionHandler(
                        mainActivity = this,
                        navController = navController,
                        tokenRefreshViewModel = tokenRefreshViewModel
                    )
                )
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
        composable(AppNavigationItem.AdminStatistics.route) {
            AdminStatisticsScreen(navController = navController)
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
        composable(
            route = AppNavigationItem.RecordDetail.route
                    + DeepLinkKey.HEADER_ID + "{${DeepLinkKey.HEADER_ID}}"
                    + DeepLinkKey.RECORD_ID + "{${DeepLinkKey.RECORD_ID}}"
                    + DeepLinkKey.Role + "{${DeepLinkKey.Role}}",
            arguments = listOf(
                navArgument(DeepLinkKey.HEADER_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(DeepLinkKey.RECORD_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(DeepLinkKey.Role) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val headerId = it.arguments?.getInt(DeepLinkKey.HEADER_ID) ?: R.string.feature_question
            val recordId = it.arguments?.getString(DeepLinkKey.RECORD_ID) ?: ""
            val role = it.arguments?.getString(DeepLinkKey.Role) ?: ""

            RecordDetailScreen(
                navController = navController,
                headerId = headerId,
                recordId = recordId,
                role = role
            )
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

    object AdminStatistics : AppNavigationItem("AdminStatistics")

    object Chat : AppNavigationItem("Chat")

    object Record : AppNavigationItem("Record")

    object RecordDetail : AppNavigationItem("RecordDetail")
}

object DeepLinkKey {
    const val KEY = "KEY"
    const val ROOM_ID = "roomId"
    const val HEADER_ID = "headerId"
    const val RECORD_ID = "recordId"
    const val Role = "role"
}
