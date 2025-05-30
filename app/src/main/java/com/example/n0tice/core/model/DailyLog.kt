package com.example.n0tice.core.model

data class DailyLog(
    val logId: Int,
    val title: String,
    val logDate: String,
    val content: String,
    val startTime: String,
    val endTime: String,
    val managerName: String,
    val agentName: String,
    val accidentRelatedNotes: String,
//    val createdAt: String,
)