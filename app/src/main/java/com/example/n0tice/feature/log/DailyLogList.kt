package com.example.n0tice.feature.log

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.model.MonthlyLog
import com.example.n0tice.core.ui.theme.Blue
import com.example.n0tice.core.ui.theme.BlueGray
import com.example.n0tice.core.ui.theme.LightGray
import com.example.n0tice.core.ui.theme.Navy
import com.example.n0tice.core.ui.theme.SecondGreen
import com.example.n0tice.core.ui.theme.Violet
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun DailyLogList(
    selectedDate: MutableState<String>,
    monthlyLogs: List<MonthlyLog>,
    openDailyLog: (Int) -> Unit
) {
    val logs = monthlyLogs.filter {
        it.logDate == selectedDate.value
    }

    LazyColumn(
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        itemsIndexed(logs) { idx, log ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 8.dp)
                    .border(1.5.dp, LightGray, RoundedCornerShape(12.dp))
                    .clickable(
                        onClick = { openDailyLog(log.logId) }
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val dotColors = when {
                            (idx == 0) -> SecondGreen
                            (idx == 1) -> Violet
                            (idx == 2) -> Blue
                            else -> Color.Red
                        }

                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .border(3.dp, dotColors, CircleShape)
                        )

                        Text(
                            text = log.startTime + "-" + log.endTime,
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            ),
                            color = BlueGray
                        )
                    }

                    Text(
                        text = log.title,
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        ),
                        color = Navy
                    )
                }
            }

        }
    }
}