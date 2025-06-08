package com.example.n0tice.feature.log

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.n0tice.core.api.n0tice.N0ticeApiService
import com.example.n0tice.core.api.n0tice.N0ticeClient
import com.example.n0tice.core.api.n0tice.dto.DailyWorkLog
import com.example.n0tice.core.api.n0tice.dto.MonthlyWorkLog
import com.example.n0tice.core.api.n0tice.dto.WorkLogRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LogViewModel : ViewModel() {
    val service = N0ticeClient.getInstance().create(N0ticeApiService::class.java)

    private val _logWriteState = MutableStateFlow(LogWriteState())
    val logWriteState: StateFlow<LogWriteState> = _logWriteState

    private val _monthlyLogs = MutableStateFlow<List<MonthlyWorkLog>>(emptyList())
    val monthlyLogs: StateFlow<List<MonthlyWorkLog>> = _monthlyLogs

    private val _dailyLog = MutableStateFlow<DailyWorkLog?>(null)
    val dailyLog: StateFlow<DailyWorkLog?> = _dailyLog

    // 특정 일자의 작업 일지를 받아오는 함수
    fun getDailyWorkLog(date: String, userId: Long) {
        Log.d("LogViewModel", "readWorkLog called: $date,$userId")

        viewModelScope.launch {
            val response = service.readWorkLog(date, userId)

            try {
                if (response.isSuccess) {
                    _dailyLog.value = response.data
                    Log.d("LogViewModel", "getWorkLog result: ${_dailyLog.value}")
                } else {
                    Log.e("LogViewModel", "getMonthlyWorkLogs Error: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("LogViewModel", "getMonthlyWorkLogs Error: ${e.message}")
            }

        }
    }

    // 월별 작업 일지를 받아오는 함수
    fun getMonthlyWorkLogs(year: String, month: String, userId: Long) {
        Log.d("LogViewModel", "getMonthlyWorkLogs called: $year, $month, $userId")

        viewModelScope.launch {
            val response = service.readMonthlyWorkLogs(year, month, userId)

            try {
                if (response.isSuccess) {
                    _monthlyLogs.value = response.data
                } else {
                    Log.e("LogViewModel", "getMonthlyWorkLogs Error: ${response.message}")
                }
            } catch (e: HttpException) {
                Log.e("LogViewModel", "getMonthlyWorkLogs Error: ${e.message}")
            } catch (e: Exception) {
                Log.e("LogViewModel", "getMonthlyWorkLogs Error: ${response.message}")
            }
        }
    }

    // 작업 일지 작성 함수
    fun createWorkLog(workLog: WorkLogRequest) {
        Log.d("LogViewModel", "createWorkLog called: $workLog")

        viewModelScope.launch {
            val response = service.createWorkLog(req = workLog)
            try {
                if (response.isSuccess) {
                    _logWriteState.value =
                        LogWriteState(isSuccess = response.isSuccess, message = response.message)
                } else {
                    _logWriteState.value =
                        LogWriteState(isSuccess = response.isSuccess, message = response.message)
                }
            } catch (e: Exception) {
                _logWriteState.value =
                    LogWriteState(isSuccess = response.isSuccess, message = response.message)
                Log.e("LogViewModel", "createWorkLog Error: ${_logWriteState.value.message}")
            }

        }
    }
}

data class LogWriteState(
    val isSuccess: Boolean? = null,
    val message: String? = null
)