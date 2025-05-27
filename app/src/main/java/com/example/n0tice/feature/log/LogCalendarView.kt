package com.example.n0tice.feature.log

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.R
import com.example.n0tice.core.ui.theme.BorderGray
import com.example.n0tice.core.ui.theme.GuideTextColor
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.preFontFamily
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

@Composable
fun LogCalendarView() {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = currentMonth.minusMonths(12)
    val endMonth = currentMonth.plusMonths(12)
    val daysOfWeek = daysOfWeek()
    val calendarState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first(),
    )

    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    val today = LocalDate.now()

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarTopSection(calendarState, coroutineScope, daysOfWeek)

        HorizontalCalendar(
            state = calendarState,
            dayContent = { day ->
                val date = day.date
                val isSelected = date == selectedDate.value
                val isToday = date == today

                Box(
                    modifier = Modifier
                        .aspectRatio(1.0f)
                        .clickable {
                            selectedDate.value = date
                        }
                        .background(
                            color = when {
                                // 선택한 날짜가 오늘일 때
                                isToday && isSelected -> MainGreen
                                isToday && !isSelected -> Color.Transparent
                                isSelected -> Color.Black
                                else -> Color.Transparent
                            },
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day.date.dayOfMonth.toString(),
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        ),
                        color = when {
                            isToday && !isSelected -> MainGreen
                            isToday || isSelected -> Color.White
                            else -> Color.Black
                        }
                    )
                }
            }
        )
    }
}


@Composable
private fun CalendarTopSection(
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
                    color = GuideTextColor
                )

            }
        }

        // 요일 표시
        Row {
            daysOfWeek.forEach { day ->
                val color = when (day) {
                    DayOfWeek.SUNDAY -> Color.Red
                    DayOfWeek.SATURDAY -> Color.Blue
                    else -> Color.Black
                }

                Text(
                    text = dayKoreanMap[day] ?: "",
                    color = GuideTextColor,
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


