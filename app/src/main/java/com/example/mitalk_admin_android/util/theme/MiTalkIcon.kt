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
        val Cancel = MiTalkIcon(
            drawableId = R.drawable.ic_cancel,
            contentDescription = "cancel"
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
        val Search = MiTalkIcon(
            drawableId = R.drawable.ic_search,
            contentDescription = "search"
        )

        @Stable
        val Up = MiTalkIcon(
            drawableId = R.drawable.ic_up,
            contentDescription = "up"
        )

        @Stable
        val Down = MiTalkIcon(
            drawableId = R.drawable.ic_down,
            contentDescription = "down"
        )

        @Stable
        val Download = MiTalkIcon(
            drawableId = R.drawable.ic_download,
            contentDescription = "download"
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

        @Stable
        val Find_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_find,
            contentDescription = "find icon"
        )

        @Stable
        val Cancel_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_cancel,
            contentDescription = "cancel icon"
        )

        @Stable
        val Copy_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_copy,
            contentDescription = "copy icon"
        )

        @Stable
        val Add_Green_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_add_green,
            contentDescription = "add icon"
        )

        @Stable
        val Two_Circle_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_two_circle,
            contentDescription = "two circle",
        )

        @Stable
        val Empty_Circle_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_empty_circle,
            contentDescription = "empty circle",
        )

        @Stable
        val Fill_Circle_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_fill_circle,
            contentDescription = "fill circle"
        )

        @Stable
        val Record_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_record,
            contentDescription = "icon record"
        )

        @Stable
        val Refactor_Icon = MiTalkIcon(
            drawableId = R.drawable.icon_refactor,
            contentDescription = "icon refactor"
        )

        @Stable
        val Navi_Arrow_Icon = MiTalkIcon(
            drawableId = R.drawable.ic_navi_arrow,
            contentDescription = "icon navi arrow"
        )

        @Stable
        val Star_On = MiTalkIcon(
            drawableId = R.drawable.img_star_on,
            contentDescription = "star on"
        )

        @Stable
        val Star_Off = MiTalkIcon(
            drawableId = R.drawable.img_star_off,
            contentDescription = "star off"
        )
    }
}