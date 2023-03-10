package com.example.mitalk_admin_android.ui.admin.header

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.ui.util.MiIconButton
import com.example.mitalk_admin_android.util.MultipleEventsCutter
import com.example.mitalk_admin_android.util.get
import com.example.mitalk_admin_android.util.theme.MiTalkAdminTypography
import com.example.mitalk_admin_android.util.theme.MiTalkColor
import com.example.mitalk_admin_android.util.theme.MiTalkIcon
import com.example.mitalk_admin_android.util.theme.Regular14NO

@Composable
fun AdminHeader(
    navController: NavController,
    title: String,
    hint: String = "",
    findOn: Boolean = false,
    findEnabled: Boolean = true,
    findOnPressed: (String) -> Unit = {},
    findOnRequest: () -> Unit = {}
) {
    var findText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp)
            .height(56.dp)
            .background(
                color = MiTalkColor.Gray
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        MiIconButton(
            onClick = {
                navController.popBackStack()
            },
        ) {
            Icon(
                painter = painterResource(id = MiTalkIcon.Back.drawableId),
                contentDescription = MiTalkIcon.Back.contentDescription,
            )
        }

        if (findOn || findText.isNotEmpty()) {
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = findText,
                onValueChange = {
                    findText = it
                    findOnPressed(it)
                },
                textStyle = MiTalkAdminTypography.regular14NO,
                decorationBox = @Composable {
                    it()
                    if (findText.isEmpty()) {
                        Regular14NO(
                            text = hint,
                            color = Color(0xFFA5A4A4)
                        )
                    }
                }
            )
        } else {
            Regular14NO(text = title)
        }

        if (findEnabled) {
            MiIconButton(
                onClick = {
                    findOnRequest()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
            ) {
                Icon(
                    painter = painterResource(id = MiTalkIcon.Find_Icon.drawableId),
                    contentDescription = MiTalkIcon.Find_Icon.contentDescription,
                )
            }
        }
    }
}

fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}

@Composable
@Preview(showBackground = true)
fun ShowAdminHeader() {
    val navController = rememberNavController()
    val focusManger = LocalFocusManager.current
    var findOn by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManger) {
                findOn = false
            }
    ) {
        AdminHeader(
            navController = navController,
            title = "????????????",
            findOn = findOn,
            findOnPressed = {},
            findOnRequest = {
                findOn = true
            }
        )
    }

}