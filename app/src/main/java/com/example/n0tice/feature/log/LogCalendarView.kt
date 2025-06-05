package com.example.n0tice.feature.log

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.ui.theme.Blue
import com.example.n0tice.core.ui.theme.BlueGray
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.SGreen
import com.example.n0tice.core.ui.theme.Violet
import com.example.n0tice.core.ui.theme.preFontFamily
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun LogCalendarView(
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    monthlyLog: Map<LocalDate, Int>?
) {
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
                val isSelected = date == LocalDate.parse(selectedDate)
                val isToday = (date == today)

                val isFromThisMonth =
                    (date.month == calendarState.firstVisibleMonth.yearMonth.month
                            && date.year == calendarState.firstVisibleMonth.yearMonth.year)

                // 일지가 존재하는 날짜만
                val logCount = monthlyLog?.get(date)

                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(1.0f)
                            .clickable { onDateSelected(date.toString()) }
                            .background(
                                color = when {
                                    // 선택한 날짜가 오늘일 때
                                    isToday && isSelected -> MainGreen
                                    isToday && !isSelected -> Color.Transparent
                                    isSelected -> Color.Black
                                    else -> Color.Transparent
                                },
                                shape = RoundedCornerShape(12.dp)
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
                                !isFromThisMonth -> BlueGray
                                isToday && !isSelected -> MainGreen
                                isToday || isSelected -> Color.White
                                else -> Color.Black
                            }
                        )
                    }

                    if (logCount != null && logCount > 0) {
                        Row(
                            modifier = Modifier.padding(top = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            val dotColors = listOf(SGreen, Violet, Blue)

                            repeat(logCount.coerceAtMost(3)) { idx ->
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .border(1.6.dp, dotColors[idx], CircleShape)
                                )
                            }
                        }
                    } else {
                        Spacer(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .height(6.dp)
                        )
                    }
                }
            }
        )
    }
}
