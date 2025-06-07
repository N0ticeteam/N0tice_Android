package com.example.n0tice.core.api.n0tice.dto

data class MonthlyWorkLog(
    val workLogList: List<WorkLog>
)

data class WorkLog(
    val logId: Long,
    val logDate: String,
    val title: String,
    val startTime: String,
    val endTime: String,
)