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
        val Plus = MiTalkIcon(
            drawableId = R.drawable.ic_plus,
            contentDescription = "plus"
        )

        @Stable
        val Send = MiTalkIcon(
            drawableId = R.drawable.ic_send,
            contentDescription = "send"
        )

        @Stable
        val Picture = MiTalkIcon(
            drawableId = R.drawable.ic_picture,
            contentDescription = "picture"
        )

        @Stable
        val Video = MiTalkIcon(
            drawableId = R.drawable.ic_video,
            contentDescription = "video"
        )

        @Stable
        val Document = MiTalkIcon(
            drawableId = R.drawable.ic_document,
            contentDescription = "document"
        )

        @Stable
        val Client = MiTalkIcon(
            drawableId = R.drawable.ic_client,
            contentDescription = "client"
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
    }
}