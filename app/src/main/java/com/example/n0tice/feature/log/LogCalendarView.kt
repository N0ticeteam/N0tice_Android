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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.ui.theme.BlueGray
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.SGreen
import com.example.n0tice.core.ui.theme.preFontFamily
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import java.time.LocalDate

@Composable
fun LogCalendarView(
    calendarState: CalendarState,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    monthlyLogExistMap: Map<LocalDate, Boolean>?
) {
    val today = LocalDate.now()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarTopSection(calendarState, coroutineScope)

        HorizontalCalendar(
            state = calendarState,
            dayContent = { day ->
                val date = day.date
                val isSelected = date == LocalDate.parse(selectedDate)
                val isToday = (date == today)

                val isFromThisMonth =
                    (date.month == calendarState.firstVisibleMonth.yearMonth.month
                            && date.year == calendarState.firstVisibleMonth.yearMonth.year)

                val hasLog = monthlyLogExistMap?.get(date) == true

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

                    if (hasLog) {
                        Row(
                            modifier = Modifier.padding(top = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .border(1.6.dp, SGreen, CircleShape)
                            )
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
