package com.example.n0tice.feature.log

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.R
import com.example.n0tice.core.ui.theme.BlueGray
import com.example.n0tice.core.ui.theme.BorderGray
import com.example.n0tice.core.ui.theme.preFontFamily
import com.kizitonwose.calendar.compose.CalendarState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.Month


@Composable
fun CalendarTopSection(
    calendarState: CalendarState,
    coroutineScope: CoroutineScope,
    daysOfWeek: List<DayOfWeek>,
) {
    val dayKoreanMap = mapOf(
        DayOfWeek.SUNDAY to "일",
        DayOfWeek.MONDAY to "월",
        DayOfWeek.TUESDAY to "화",
        DayOfWeek.WEDNESDAY to "수",
        DayOfWeek.THURSDAY to "목",
        DayOfWeek.FRIDAY to "금",
        DayOfWeek.SATURDAY to "토",
    )
    val monthKoreanMap = mapOf(
        Month.JANUARY to "1월",
        Month.FEBRUARY to "2월",
        Month.MARCH to "3월",
        Month.APRIL to "4월",
        Month.MAY to "5월",
        Month.JUNE to "6월",
        Month.JULY to "7월",
        Month.AUGUST to "8월",
        Month.SEPTEMBER to "9월",
        Month.OCTOBER to "10월",
        Month.NOVEMBER to "11월",
        Month.DECEMBER to "12월",
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .border((1.6).dp, BorderGray, RoundedCornerShape(14.dp))
                    .size(40.dp),
                onClick = {
                    coroutineScope.launch {
                        calendarState.animateScrollToMonth(
                            calendarState.firstVisibleMonth.yearMonth.minusMonths(1)
                        )
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.left_arrow),
                    contentDescription = null
                )
            }

            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .border((1.6).dp, BorderGray, RoundedCornerShape(14.dp))
                    .size(40.dp),
                onClick = {
                    coroutineScope.launch {
                        calendarState.animateScrollToMonth(
                            calendarState.firstVisibleMonth.yearMonth.plusMonths(1)
                        )
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.right_arrow),
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = monthKoreanMap[calendarState.firstVisibleMonth.yearMonth.month] ?: "",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                    )
                )

                Text(
                    text = calendarState.firstVisibleMonth.yearMonth.year.toString(),
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                    ),
                    color = BlueGray
                )

            }
        }

        // 요일 표시
        Row {
            daysOfWeek.forEach { day ->
                Text(
                    text = dayKoreanMap[day] ?: "",
                    color = BlueGray,
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}