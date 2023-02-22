package com.example.mitalk_admin_android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mitalk_admin_android.ui.util.MiHeader
import com.example.mitalk_admin_android.util.theme.Bold13NO
import com.example.mitalk_admin_android.util.theme.Bold20NO
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.util.*
import com.example.mitalk_admin_android.util.theme.MiTalkColor
import com.example.mitalk_admin_android.vm.SettingViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    vm: SettingViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var settingLanguageVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        vm.fetchLanguage()
    }

    Column {
        MiHeader(backPressed = {
            navController.popBackStack()
        })
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(top = 30.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(5.dp))
                .miClickable {
                    settingLanguageVisible = true
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 15.dp)
            ) {
                Bold20NO(text = stringResource(id = R.string.setting_language))
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 15.dp)
            ) {
                Bold13NO(
                    text = stringResource(id = state.language.changeLanguageId())
                )
            }
        }
        LanguageDialog(visible = settingLanguageVisible, onDismissRequest = {
            settingLanguageVisible = false
        }, onChangeAction = {
            it.changeLanguage(context)
            vm.saveLanguage(it)
            vm.fetchLanguage()
        })

    }
}

@Composable
fun LanguageDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onChangeAction: (String) -> Unit
) {
    if (visible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .width(280.dp)
                    .height(170.dp)
                    .background(color = MiTalkColor.White, shape = RoundedCornerShape(5.dp)),
                verticalArrangement = Arrangement.Center
            ) {
                Bold20NO(
                    text = stringResource(id = R.string.korean),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .miClickable {
                            onChangeAction(Korean)
                            onDismissRequest()
                        })
                Spacer(modifier = Modifier.height(20.dp))
                Bold20NO(
                    text = stringResource(id = R.string.english),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .miClickable {
                            onChangeAction(English)
                            onDismissRequest()
                        })
            }
        }
    }
}