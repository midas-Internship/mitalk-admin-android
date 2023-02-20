package com.example.mitalk_admin_android.ui.util

import androidx.compose.ui.graphics.Color
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.util.theme.MiTalkColor

enum class RecordItemType(
    val type: String,
    val textColor: Color,
    val titleId: Int
) {
    QUESTION(
        type = "FEATURE_QUESTION",
        textColor = Color(0xFFD49393),
        titleId = R.string.feature_question
    ),
    FEEDBACK(
        type = "FEEDBACK",
        textColor = MiTalkColor.MainBlue,
        titleId = R.string.product_feedback
    ),
    BUG(
        type = "BUG",
        textColor = MiTalkColor.MainGreen,
        titleId = R.string.bug_report
    ),
    SUGGEST(
        type = "FEATURE_PROPOSAL",
        textColor = MiTalkColor.MainBrown,
        titleId = R.string.feature_proposal
    ),
    INQUIRY(
        type = "PURCHASE",
        textColor = Color(0xFFA1A0DF),
        titleId = R.string.purchase
    ),
    ETC(
        type = "ETC",
        textColor = Color(0xFFC294C2),
        titleId = R.string.etc
    ),
    ElSE(
        type = "",
        textColor = MiTalkColor.Black,
        titleId = R.string.feature_question
    ),
}