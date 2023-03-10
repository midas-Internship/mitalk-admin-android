package com.example.mitalk_admin_android.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.AppNavigationItem
import com.example.mitalk_admin_android.DeepLinkKey
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.mvi.LoginSideEffect
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.observeWithLifecycle
import com.example.mitalk_admin_android.util.theme.*
import com.example.mitalk_admin_android.util.theme.MiTalkColor
import com.example.mitalk_admin_android.vm.LoginViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@OptIn(InternalCoroutinesApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = hiltViewModel(),
) {
    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    sideEffect.observeWithLifecycle {
        when (it) {
            is LoginSideEffect.LoginSuccess -> {
                if (it.role == "ADMIN") {
                    navController.navigate(
                        route = AppNavigationItem.AdminMain.route
                                + DeepLinkKey.KEY + it.key
                    ) {
                        popUpTo(0)
                    }
                } else if (it.role == "COUNSELLOR") {
                    navController.navigate(
                        route = AppNavigationItem.CounsellorMain.route
                    ) {
                        popUpTo(0)
                    }
                }
            }
        }
    }

    LaunchedEffect(vm) {
        vm.autoSignIn()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(42.dp))
        Image(
            painter = painterResource(id = MiTalkIcon.Login_Img.drawableId),
            contentDescription = MiTalkIcon.Login_Img.contentDescription,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
        ) {
            CommaAboveText(text = "??? ")
            CommaAboveText(text = "??? ")
            CommaAboveText(text = "??? ")
            Medium21GM(text = "   ??? ???   ??? ???")
        }
        Spacer(modifier = Modifier.height(62.dp))
        LoginTextFiled(
            value = state.certificationNumber,
            onValueChanged = { vm.inputCertificationNumber(it) }
        )
        Spacer(modifier = Modifier.height(11.dp))
        LoginBtn { vm.signIn(certificationNumber = state.certificationNumber) }
    }
}

@Composable
private fun CommaAboveText(text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = MiTalkIcon.Comma.drawableId),
            contentDescription = MiTalkIcon.Comma.contentDescription,
        )
        Medium21GM(text = text)
    }
}
@Stable
private val LoginTextShape = RoundedCornerShape(4.dp)

@Composable
private fun LoginTextFiled(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(
                color = MiTalkColor.White,
                shape = LoginTextShape,
            )
            .border(
                width = 1.dp,
                color = Color(0xFF141414),
                shape = LoginTextShape,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = value,
            onValueChange = onValueChanged,
            textStyle = MiTalkAdminTypography.regular12NO,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default,
            ),
            decorationBox = @Composable {
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    it()

                    if (value.isEmpty()) {
                        Regular12NO(text = stringResource(id = R.string.input_certification_number))
                    }
                }
            }
        )
    }
}

@Composable
private fun LoginBtn(
    onPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(
                color = Color(0xFF7A6A9B),
                shape = LoginTextShape,
            )
            .clip(shape = LoginTextShape)
            .miClickable { onPressed() },
        contentAlignment = Alignment.Center
    ) {
        Bold12NO(
            text = "Log in",
            color = MiTalkColor.White,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShowLoginScreen() {
    val rememberController = rememberNavController()
    LoginScreen(navController = rememberController)
}