@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.n0tice.feature.log

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.n0tice.R
import com.example.n0tice.core.api.n0tice.dto.MonthlyWorkLog
import com.example.n0tice.core.api.n0tice.dto.WorkLogRequest
import com.example.n0tice.core.ui.theme.MainGreen
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogScreen(
    logViewModel: LogViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    val logWriteState = logViewModel.logWriteState.collectAsState().value
    val monthlyLogs = logViewModel.monthlyLogs.collectAsState().value
    val dailyLog = logViewModel.dailyLog.collectAsState().value

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showLogWriteSheet by remember { mutableStateOf(false) }

    // 날짜는 스트링으로 관리
    var selectedDate by remember { mutableStateOf(LocalDate.now().toString()) }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    // 날짜/시간 dialog 관리
    var showDatePicker by remember { mutableStateOf(false) }
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }

    val logExistenceMap: Map<LocalDate, Boolean> =
        monthlyLogs.associate { LocalDate.parse(it.logDate) to true }

    // 해당 월의 작업 일지
    val log: MonthlyWorkLog? = monthlyLogs.find {
        it.logDate == selectedDate
    }

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

    // 달이 바뀔 때마다 월간 일지를 가져온다
    LaunchedEffect(calendarState.firstVisibleMonth.yearMonth) {
        val newMonth = calendarState.firstVisibleMonth.yearMonth
        val today = LocalDate.now()

        selectedDate = if (newMonth == YearMonth.from(today)) {
            today.toString() // 현재 달이면 오늘 날짜로
        } else {
            newMonth.atDay(1).toString() // 아니면 해당 달의 1일로
        }

        logViewModel.getMonthlyWorkLogs(
            year = calendarState.firstVisibleMonth.yearMonth.year.toString(),
            month = calendarState.firstVisibleMonth.yearMonth.monthValue.toString(),
            userId = 1
        )
    }

    if (logWriteState.isSuccess == false) {
        Toast.makeText(context, "일지 작성에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
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
                calendarState = calendarState,
                selectedDate = selectedDate,
                onDateSelected = {
                    selectedDate = it
                },
                monthlyLogExistMap = logExistenceMap
            )

            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                // 일지가 존재하면 일지 표시
                // 일지가 존재하지 않으면 일지 추가 버튼 표시
                if (log != null) {
                    logViewModel.getDailyWorkLog(log.logDate, 1)
                    dailyLog?.let { WorkLogView(dailyLog) }
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
        if (showLogWriteSheet) {
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
                        selectedDate = selectedDate,
                        startTime = startTime,
                        endTime = endTime,
                        openDatePicker = { showDatePicker = !showDatePicker },
                        openStartTimePicker = { showStartTimePicker = !showStartTimePicker },
                        openEndTimePicker = { showEndTimePicker = !showEndTimePicker },
                        close = { workLogReq ->

                            // 산재 특이사항을 제외한 나머지 필드가 채워져있는지 확인
                            if (!isWorkLogValid(workLogReq)) {
                                Toast.makeText(context, "모든 필수 항목을 입력해주세요.", Toast.LENGTH_SHORT).show()
                                return@LogWriteSheet
                            }

                            // 작성하려는 날짜에 일지가 있는지 확인
                            if (monthlyLogs.any { it.logDate == selectedDate }) {
                                Toast.makeText(context, "이미 작성된 일지가 있습니다.", Toast.LENGTH_SHORT).show()
                                return@LogWriteSheet
                            }

                            logViewModel.createWorkLog(workLogReq)
                            showLogWriteSheet = false
                        }
                    )
                }
            }

            if (showDatePicker) {
                LogDatePicker(
                    onDateSelected = { dateStr ->
                        selectedDate = dateStr ?: selectedDate
                    },
                    onDismiss = { showDatePicker = false }
                )
            }

            if (showStartTimePicker) {
                LogTimePicker(
                    onConfirm = { time ->
                        startTime = time
                    },
                    onDismiss = { showStartTimePicker = false }
                )
            }

            if (showEndTimePicker) {
                LogTimePicker(
                    onConfirm = { time ->
                        endTime = time
                    },
                    onDismiss = { showEndTimePicker = false }
                )
            }
        }
    }
}

private fun isWorkLogValid(workLog: WorkLogRequest): Boolean {
    return workLog.title.isNotBlank()
            && workLog.content.isNotBlank()
            && workLog.logDate.isNotBlank()
            && workLog.startTime.isNotBlank()
            && workLog.endTime.isNotBlank()
            && workLog.managerName.isNotBlank()
            && workLog.agentName.isNotBlank()
}
