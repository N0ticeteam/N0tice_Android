package com.example.n0tice.core.api.n0tice.dto

data class DailyWorkLog(
    val logId: Long,
    val title: String,
    val logDate: String,
    val content: String,
    val startTime: String,
    val endTime: String,
    val managerName: String,
    val agentName: String,
    val accidentRelatedNotes: String,
    val createdAt: String
)
