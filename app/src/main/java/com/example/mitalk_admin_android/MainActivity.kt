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
import com.example.mitalk_admin_android.ui.SettingScreen
import com.example.mitalk_admin_android.ui.admin.issued.AdminIssuedScreen
import com.example.mitalk_admin_android.ui.admin.usercare.AdminUserCareScreen
import com.example.mitalk_admin_android.ui.admin.messagerecord.AdminMessageRecordScreen
import com.example.mitalk_admin_android.ui.counsellor.chat.ChatScreen
import com.example.mitalk_admin_android.ui.admin.question.AdminQuestionScreen
import com.example.mitalk_admin_android.ui.counsellor.main.CounsellorMainScreen
import com.example.mitalk_admin_android.ui.admin.statistics.AdminStatisticsScreen
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
        composable(AppNavigationItem.Chat.route) {
            ChatScreen(navController = navController, vm = viewModel)
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
                    + DeepLinkKey.Role + "{${DeepLinkKey.Role}}"
                    + DeepLinkKey.CounsellorName + "{${DeepLinkKey.CounsellorName}}"
                    + DeepLinkKey.CustomerName + "{${DeepLinkKey.CustomerName}}",
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
                },
                navArgument(DeepLinkKey.CounsellorName) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(DeepLinkKey.CustomerName) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val headerId = it.arguments?.getInt(DeepLinkKey.HEADER_ID) ?: R.string.feature_question
            val recordId = it.arguments?.getString(DeepLinkKey.RECORD_ID) ?: ""
            val role = it.arguments?.getString(DeepLinkKey.Role) ?: ""
            val counsellorName = it.arguments?.getString(DeepLinkKey.CounsellorName) ?: ""
            val customerName = it.arguments?.getString(DeepLinkKey.CustomerName) ?: ""

            RecordDetailScreen(
                navController = navController,
                headerId = headerId,
                recordId = recordId,
                role = role,
                counsellorName = counsellorName,
                customerName = customerName
            )
        }
        composable(AppNavigationItem.Setting.route) {
            SettingScreen(navController = navController)
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

    object Setting : AppNavigationItem("Setting")
}

object DeepLinkKey {
    const val KEY = "KEY"
    const val HEADER_ID = "headerId"
    const val RECORD_ID = "recordId"
    const val Role = "role"
    const val CounsellorName = "counsellorName"
    const val CustomerName = "customerName"
}
