package com.example.n0tice.core.components

import android.util.Log
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.n0tice.core.ui.theme.LightGreen
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.preFontFamily
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogDatePicker(
    onDateSelected: (String?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                // Long? --> yyyy-MM-dd String?
                val dateStr = datePickerState.selectedDateMillis?.let { convertMillisToDate(it) }
                onDateSelected(dateStr)
                onDismiss()
            }) {
                Text(
                    text = "확인",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                    ),
                    color = Color.Black
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = "취소",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                    ),
                    color = Color.Black
                )
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = LightGreen,
        ),
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = MainGreen, // 선택된 날짜의 날짜 부분 배경색 설정
                todayDateBorderColor = MainGreen, // 오늘 날짜의 테두리 색상 설정
                todayContentColor = MainGreen, // 오늘 날짜의 텍스트 색상 설정

            )
        )
    }
}

// 밀리 초를 스트링으로 변환
private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    Log.d("Calendar", "before: $millis, after: ${formatter.format(Date(millis))}")
    return formatter.format(Date(millis))
}
