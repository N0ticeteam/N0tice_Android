package com.example.n0tice.core.api.n0tice.dto

data class MonthlyWorkLog(
    val logId: Long,
    val logDate: String,
    val title: String,
    val startTime: String,
    val endTime: String,
)