package com.example.mitalk_admin_android.util.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.example.mitalk_admin_android.R
import javax.annotation.concurrent.Immutable

@Immutable
class MiTalkIcon private constructor(
    @DrawableRes val drawableId: Int,
    val contentDescription: String? = null,
) {

    companion object {
        @Stable
        val Login_Img = MiTalkIcon(
            drawableId = R.drawable.img_login,
            contentDescription = "login img",
        )
        @Stable
        val Comma = MiTalkIcon(
            drawableId = R.drawable.comma,
            contentDescription = "comma",
        )

        @Stable
        val Back = MiTalkIcon(
            drawableId = R.drawable.back,
            contentDescription = "back"
        )

        @Stable
        val Logo = MiTalkIcon(
            drawableId = R.drawable.img_logo,
            contentDescription = "logo"
        )

        @Stable
        val Counsellor_On_Img = MiTalkIcon(
            drawableId = R.drawable.img_counsellor_on,
            contentDescription = "counsellor on"
        )
        @Stable
        val Counsellor_Off_Img = MiTalkIcon(
            drawableId = R.drawable.img_counsellor_off,
            contentDescription = "counsellor off"
        )
    }
}