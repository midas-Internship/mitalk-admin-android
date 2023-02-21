package com.example.mitalk_admin_android.ui.admin.statistics

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.ui.admin.header.AdminHeader
import com.example.mitalk_admin_android.ui.admin.header.addFocusCleaner

@Composable
fun AdminStatisticsScreen(
    navController: NavController,
) {
    val focusManager = LocalFocusManager.current
    var findOn by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager) {
                findOn = false
            }
    )
     {
        AdminHeader(
            navController = navController,
            title = stringResource(id = R.string.admin_graph_title),
            hint = stringResource(id = R.string.input_search),
            findOn = findOn,
            findOnPressed = {
                searchText = it
            },
            findOnRequest = {
                findOn = true
            }
        )

         LazyColumn(
             modifier = Modifier
                 .padding(32.dp)
                 .fillMaxWidth()
         ) {

         }
    }
}

@Composable
private fun StatisticsItem(
    name: String,
    id: String,
    star: String,
) {
    Row {

    }
}