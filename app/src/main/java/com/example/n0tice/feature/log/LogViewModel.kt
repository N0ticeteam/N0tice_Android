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

class LogViewModel : ViewModel() {
    val service = N0ticeClient.getInstance().create(N0ticeApiService::class.java)

    private val _logWriteState = MutableStateFlow(LogWriteState())
    val logWriteState: StateFlow<LogWriteState> = _logWriteState

    private val _monthlyLogs = MutableStateFlow<List<MonthlyWorkLog>>(emptyList())
    val monthlyLogs: StateFlow<List<MonthlyWorkLog>> = _monthlyLogs

    private val _dailyLog = MutableStateFlow<DailyWorkLog?>(null)
    val dailyLog: StateFlow<DailyWorkLog?> = _dailyLog

    // 특정 일자의 작업 일지를 받아오는 함수
    fun getDailyWorkLog(date: String, userId: String) {
        Log.d("LogViewModel", "readWorkLog called: $date,$userId")

        viewModelScope.launch {
            try {
                val response = service.readWorkLog(date, userId)

                if (response.isSuccessful) {

                    val body = response.body()
                    if (body != null && body.isSuccess) {
                        _dailyLog.value = body.data
                    } else {
                        _dailyLog.value = null

                        val errorBody = response.errorBody()?.string()
                        Log.e("LogViewModel", "HTTP Error: ${response.code()} $errorBody")
                    }
                } else {
                    _dailyLog.value = null

                    val errorBody = response.errorBody()?.string()
                    Log.e("LogViewModel", "HTTP Error: ${response.code()} $errorBody")
                }
            } catch (e: Exception) {
                _dailyLog.value = null
                Log.e("LogViewModel", "getDailyWorkLog failed: ${e.message}")
            }
        }
    }

    // 월별 작업 일지를 받아오는 함수
    fun getMonthlyWorkLogs(year: String, month: String, userId: String) {
        Log.d("LogViewModel", "getMonthlyWorkLogs called: $year, $month, $userId")

        viewModelScope.launch {
            try {
                val response = service.readMonthlyWorkLogs(year, month, userId)

                if (response.isSuccessful) {

                    val body = response.body()
                    if (body != null && body.isSuccess) {
                        _monthlyLogs.value = body.data
                    } else {
                        _monthlyLogs.value = emptyList()

                        val errorBody = response.errorBody()?.string()
                        Log.e("LogViewModel", "HTTP Error: ${response.code()} $errorBody")
                    }
                } else {
                    _monthlyLogs.value = emptyList()

                    val errorBody = response.errorBody()?.string()
                    Log.e("LogViewModel", "HTTP Error: ${response.code()} $errorBody")
                }
            } catch (e: Exception) {
                _monthlyLogs.value = emptyList()

                Log.e("LogViewModel", "getMonthlyWorkLogs Error: ${e.message}")
            }
        }
    }

    // 작업 일지 작성 함수
    fun createWorkLog(workLog: WorkLogRequest) {
        Log.d("LogViewModel", "createWorkLog called: $workLog")

        viewModelScope.launch {
            try {
                val response = service.createWorkLog(req = workLog)

                if (response.isSuccessful) {

                    val body = response.body()
                    if (body != null && body.isSuccess) {
                        _logWriteState.value =
                            LogWriteState(isSuccess = body.isSuccess, message = body.message)
                    } else {
                        _logWriteState.value = LogWriteState(isSuccess = false)

                        val errorBody = response.errorBody()?.string()
                        Log.e("LogViewModel", "HTTP Error: ${response.code()} $errorBody")
                    }

                } else {
                    _logWriteState.value = LogWriteState(isSuccess = false)

                    val errorBody = response.errorBody()?.string()
                    Log.e("RiskViewModel", "HTTP Error: ${response.code()} $errorBody")
                }
            } catch (e: Exception) {
                _logWriteState.value = LogWriteState(isSuccess = false)
                Log.e("LogViewModel", "createWorkLog Error: ${e.message}")
            }

        }
    }
}

data class LogWriteState(
    val isSuccess: Boolean? = null,
    val message: String? = null
)