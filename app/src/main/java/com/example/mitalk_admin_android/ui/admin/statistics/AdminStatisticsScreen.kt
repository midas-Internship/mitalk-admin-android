package com.example.mitalk_admin_android.ui.admin.statistics

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.entity.admin.StatisticsDetailEntity
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.ui.admin.header.AdminHeader
import com.example.mitalk_admin_android.ui.admin.header.addFocusCleaner
import com.example.mitalk_admin_android.ui.util.bottomBorder
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.theme.*
import com.example.mitalk_admin_android.vm.admin.AdminStatisticsViewModel
import kotlin.math.floor
import kotlin.math.round

@Stable
private val ItemMainColor = Color(0xFF5F5677)

@Composable
fun AdminStatisticsScreen(
    navController: NavController,
    vm: AdminStatisticsViewModel = hiltViewModel(),
) {
    val container = vm.container
    val state = container.stateFlow.collectAsState().value

    LaunchedEffect(vm) {
        vm.getStatisticsList()
    }

    val focusManager = LocalFocusManager.current
    var findOn by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager) {
                findOn = false
            }
    )
     {
        AdminHeader(
            navController = navController,
            title = stringResource(id = R.string.admin_graph_title),
            hint = stringResource(id = R.string.input_search),
            findOn = findOn,
            findOnPressed = {
                searchText = it
            },
            findOnRequest = {
                findOn = true
            }
        )
         
         Spacer(modifier = Modifier.height(20.dp))

         LazyColumn(
             modifier = Modifier
                 .padding(horizontal = 16.dp)
                 .fillMaxWidth(),
             verticalArrangement = Arrangement.spacedBy(18.dp)
         ) {
            items(state.listStatistics) {
                it.run {
                    if (name.contains(searchText) || id.contains(searchText)) {
                        StatisticsItem(
                            name = name,
                            id = id,
                            star = star,
                            reviewList = state.reviewList,
                            messageList = state.messageList,
                        ) {
                            vm.getStatisticsDetailList(id)
                        }
                    }
                }
            }
         }
    }
}

@Composable
private fun StatisticsItem(
    name: String,
    id: String,
    star: Float,
    reviewList: List<StatisticsDetailEntity.ReviewsData>,
    messageList: List<String>,
    onOpenClick: (String) -> Unit,
) {
    var open by remember { mutableStateOf(false) }
    var targetValue by remember { mutableStateOf(0F) }
    val rotateValue: Float by animateFloatAsState(
        targetValue = targetValue,
        tween(500)
    )

    val goodReview: ArrayList<StatisticsDetailEntity.ReviewsData> = ArrayList()
    val badReview: ArrayList<StatisticsDetailEntity.ReviewsData> = ArrayList()

    for (element in reviewList) {
        when (element.reviewItem) {
            "KINDNESS" -> goodReview.add(element.toChange(stringResource(id = R.string.kindness)))
            "EXPLANATION" -> goodReview.add(element.toChange(stringResource(id = R.string.explanation)))
            "USEFUL" -> goodReview.add(element.toChange(stringResource(id = R.string.useful)))
            "COMFORT" -> goodReview.add(element.toChange(stringResource(id = R.string.comfort)))
            "LISTEN" -> goodReview.add(element.toChange(stringResource(id = R.string.listen)))
            "FAST_ANSWER" -> goodReview.add(element.toChange(stringResource(id = R.string.fast_answer)))

            "UNKINDNESS" -> badReview.add(element.toChange(stringResource(id = R.string.unkindness)))
            "DIFFICULT_EXPLANATION" -> badReview.add(element.toChange(stringResource(id = R.string.difficult_explanation)))
            "USELESS" -> badReview.add(element.toChange(stringResource(id = R.string.useless)))
            "SLANG" -> badReview.add(element.toChange(stringResource(id = R.string.slang)))
            "NOT_APPROPRIATE_ANSWER" -> badReview.add(element.toChange(stringResource(id = R.string.not_appropriate_answer)))
            "SLOW_ANSWER" -> badReview.add(element.toChange(stringResource(id = R.string.slow_answer)))
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = MiTalkColor.Gray)
        .border(
            width = 1.dp,
            color = ItemMainColor,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .miClickable(
                    rippleEnabled = false
                ) {
                    targetValue =
                        if (open) {
                            0f
                        } else {
                            onOpenClick(id)
                            90F
                        }
                    open = !open
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Medium12NO(
                        text = name,
                        color = Color(0xFF736B88)
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    StartList(star = star)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Medium07NO(
                    text = id,
                    color = ItemMainColor,
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
            Image(
                painter = painterResource(id = MiTalkIcon.Navi_Arrow_Icon.drawableId),
                contentDescription = MiTalkIcon.Navi_Arrow_Icon.contentDescription,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
                    .rotate(rotateValue),
            )
        }

        if (open) {
            Spacer(modifier = Modifier.height(19.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomBorder(
                        strokeWidth = 1.dp,
                        color = ItemMainColor
                    )
            ) {
                goodReview.sortBy { it.percentage }
                goodReview.reverse()
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth(0.5f)
                        .height(100.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(goodReview) {
                        ReviewItem(
                            review = it.reviewItem,
                            percent = it.percentage
                        )
                    }
                }
                badReview.sortBy { it.percentage }
                goodReview.reverse()
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth()
                        .height(100.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(badReview) {
                        ReviewItem(
                            review = it.reviewItem,
                            percent = it.percentage
                        )
                    }
                }
            }
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                }
            }
        }
    }
}

@Composable
private fun StartList(
    star: Float,
) {
    LazyRow {
        items(listOf(1,2,3,4,5)) {
            val painter =
                if (round(star) >= it) painterResource(id = MiTalkIcon.Star_On.drawableId)
                else painterResource(id = MiTalkIcon.Star_Off.drawableId)
            Image(
                painter = painter,
                contentDescription = "star list image",
                modifier = Modifier.size(13.dp)
            )
        }
    }
}

@Composable
private fun ReviewItem(
    review: String,
    percent: Float,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .bottomBorder(
                strokeWidth = 1.dp,
                color = ItemMainColor
            )
    ) {
        Bold12NO(
            text = review,
            color = ItemMainColor,
        )

        Bold12NO(
            text = buildAnnotatedString {
                append(floor(percent).toString())
                append("%")
            }.toString(),
            color = ItemMainColor,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

private fun StatisticsDetailEntity.ReviewsData.toChange(
    review: String
) = StatisticsDetailEntity.ReviewsData(
    reviewItem = review,
    percentage = percentage
)