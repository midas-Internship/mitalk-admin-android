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
        val Profile = MiTalkIcon(
            drawableId = R.drawable.icon_profile,
            contentDescription = "profile"
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

        @Stable
        val Counsellor_Open_Record_Img = MiTalkIcon(
            drawableId = R.drawable.img_counsellor_open_record,
            contentDescription = "counsellor open record"
        )

        @Stable
        val Question_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_question,
            contentDescription = "question icon"
        )

        @Stable
        val Graph_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_graph,
            contentDescription = "graph icon"
        )

        @Stable
        val Issued_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_issued,
            contentDescription = "issued icon"
        )

        @Stable
        val User_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_user,
            contentDescription = "use icon"
        )

        @Stable
        val Message_Record_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_message_record,
            contentDescription = "message record icon"
        )

        @Stable
        val Logout_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_logout,
            contentDescription = "logout icon"
        )

    }
}