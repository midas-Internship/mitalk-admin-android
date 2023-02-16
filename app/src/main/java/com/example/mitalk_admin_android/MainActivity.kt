package com.example.mitalk_admin_android

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.sample.ui.SampleScreen
import com.example.mitalk_admin_android.sample.ui.admin.AdminMainScreen
import com.example.mitalk_admin_android.ui.LoginScreen
import com.example.mitalk_admin_android.util.theme.MitalkadminandroidTheme
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

    NavHost(navController = navController, startDestination = AppNavigationItem.Login.route) {
        composable(AppNavigationItem.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(AppNavigationItem.AdminMain.route) {
            AdminMainScreen(navController = navController)
        }
    }
}

sealed class AppNavigationItem(val route: String) {
    object Login : AppNavigationItem("Login")

    object AdminMain : AppNavigationItem("AdminMain")
}
