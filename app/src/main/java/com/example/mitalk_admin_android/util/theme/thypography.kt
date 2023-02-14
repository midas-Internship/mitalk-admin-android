package com.example.mitalk_admin_android.util.theme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.util.theme.base.MiTalkColor

internal val notoSansKR = FontFamily(
    Font(R.font.noto_sans_kr_black, FontWeight.Black),
    Font(R.font.noto_sans_kr_bold, FontWeight.Bold),
    Font(R.font.noto_sans_kr_light, FontWeight.Light),
    Font(R.font.noto_sans_kr_medium, FontWeight.Medium),
    Font(R.font.noto_sans_kr_regular, FontWeight.Normal),
    Font(R.font.noto_sans_kr_thin, FontWeight.Thin),
)

internal val gmartketSans = FontFamily(
    Font(R.font.gmarket_sans_light, FontWeight.Light),
    Font(R.font.gmarket_sans_medium, FontWeight.Medium),
    Font(R.font.gmarket_sans_bold, FontWeight.Bold)
)

object MiTalkAdminTypography {

    @Stable
    val regular12NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
}

@Composable
fun Regular12NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.regular12NO,
        color = color,
        textAlign = textAlign,
    )
}