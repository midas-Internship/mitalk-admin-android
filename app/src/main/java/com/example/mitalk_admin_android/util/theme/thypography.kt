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
    val light11NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Light,
        fontSize = 11.sp
    )

    @Stable
    val light20NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp
    )

    @Stable
    val regular10NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )

    @Stable
    val regular12NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

    @Stable
    val regular14NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )

    @Stable
    val regular16NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    @Stable
    val regular25NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp
    )

    @Stable
    val medium07NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Medium,
        fontSize = 7.sp
    )

    @Stable
    val medium12NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )

    @Stable
    val medium13NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
    )

    @Stable
    val medium17NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    )

    @Stable
    val bold12NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )

    @Stable
    val bold13NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
    )

    @Stable
    val bold20NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )

    @Stable
    val light13GM = TextStyle(
        fontFamily = gmartketSans,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp
    )

    @Stable
    val medium21GM = TextStyle(
        fontFamily = gmartketSans,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
    )
}

@Composable
fun Light11NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.light11NO,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun Light20NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.light20NO,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun Regular10NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.regular10NO,
        color = color,
        textAlign = textAlign,
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

@Composable
fun Regular14NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.regular14NO,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun Regular16NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.regular16NO,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun Regular25NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.regular25NO,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun Medium07NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.medium07NO,
        color = color,
        textAlign = textAlign,
    )
}
@Composable
fun Medium12NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.medium12NO,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun Medium13NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.medium13NO,
        color = color,
        textAlign = textAlign,
    )
}
@Composable
fun Medium17NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.medium17NO,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun Bold12NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.bold12NO,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun Bold13NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.bold13NO,
        color = color,
        textAlign = textAlign,
    )
}


@Composable
fun Bold20NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.bold20NO,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun Light13GM(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.light13GM,
        color = color,
        textAlign = textAlign,
    )
}
@Composable
fun Medium21GM(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MiTalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkAdminTypography.medium21GM,
        color = color,
        textAlign = textAlign,
    )
}

