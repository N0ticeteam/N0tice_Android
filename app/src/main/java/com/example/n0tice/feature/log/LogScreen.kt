@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.n0tice.feature.log

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.n0tice.R
import com.example.n0tice.core.model.MonthlyLog
import com.example.n0tice.core.ui.theme.MainGreen
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogScreen(
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showLogWriteSheet by remember { mutableStateOf(false) }
    var showLogReadSheet by remember { mutableStateOf(false) }

    val title = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }

    // 날짜는 스트링으로 관리
    val selectedDate = remember { mutableStateOf(LocalDate.now().toString()) }

    val startTime = remember { mutableStateOf("") }
    val endTime = remember { mutableStateOf("") }

    val supervisor = remember { mutableStateOf("") }
    val deputy = remember { mutableStateOf("") }
    val notes = remember { mutableStateOf("") }

    // 날짜/시간 dialog 관리
    var showDatePicker by remember { mutableStateOf(false) }
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }

    val dummyMonthlyLogs = listOf(
        MonthlyLog(10, "2025-06-06", "건물 외벽 3층 부분 도장", "09:00", "18:00"),
        MonthlyLog(11, "2025-05-15", "지붕 방수 처리", "10:00", "17:00"),
        MonthlyLog(12, "2025-05-30", "지붕 방수 처리", "10:00", "17:00"),
    )

    val logExistenceMap: Map<LocalDate, Boolean> = dummyMonthlyLogs
        .associate { LocalDate.parse(it.logDate) to true }

    // 일지 목록
    val log = dummyMonthlyLogs.filter {
        it.logDate == selectedDate.value
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LogCalendarView(
                selectedDate = selectedDate.value,
                onDateSelected = { selectedDate.value = it },
                monthlyLogExistMap = logExistenceMap
            )

            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                if (log.isNotEmpty()) {
                    DailyLog(showLogReadSheet, log)
                } else {
                    FloatingActionButton(
                        onClick = { showLogWriteSheet = !showLogWriteSheet },
                        shape = CircleShape,
                        containerColor = MainGreen,
                        modifier = Modifier
                            .padding(top = 35.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_plus),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }


        // 일지 작성 시트
        if (showLogWriteSheet || showLogReadSheet) {
            ModalBottomSheet(
                onDismissRequest = { showLogWriteSheet = false },
                sheetState = sheetState,
                dragHandle = null,
                windowInsets = WindowInsets(0), // 시스템 인셋 무시
                containerColor = Color.White
            ) {
                if (showLogWriteSheet) {
                    // 일지 작성 시트
                    LogWriteSheet(
                        title = title,
                        content = content,
                        selectedDate = selectedDate,
                        openDatePicker = { showDatePicker = !showDatePicker },
                        startTime = startTime,
                        openStartTimePicker = { showStartTimePicker = !showStartTimePicker },
                        endTime = endTime,
                        openEndTimePicker = { showEndTimePicker = !showEndTimePicker },
                        supervisor = supervisor,
                        deputy = deputy,
                        notes = notes,
                        close = { showLogWriteSheet = false },
                    )
                } else if (showLogReadSheet) {
                    // show.. log detail

                }
            }

            if (showDatePicker) {
                LogDatePicker(
                    onDateSelected = { dateStr ->
                        selectedDate.value = dateStr ?: selectedDate.value
                    },
                    onDismiss = { showDatePicker = false }
                )
            }

            if (showStartTimePicker) {
                LogTimePicker(
                    onConfirm = { time ->
                        startTime.value = time
                    },
                    onDismiss = { showStartTimePicker = false }
                )
            }

            if (showEndTimePicker) {
                LogTimePicker(
                    onConfirm = { time ->
                        endTime.value = time
                    },
                    onDismiss = { showEndTimePicker = false }
                )
            }
        }
    }
}
