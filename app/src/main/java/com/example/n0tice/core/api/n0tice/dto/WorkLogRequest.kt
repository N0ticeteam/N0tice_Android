package com.example.n0tice.core.api.n0tice.dto

data class WorkLogRequest(
    val userId: String,
    val logDate: String,
    val title: String,
    val content: String,
    val startTime: String,
    val endTime: String,
    val managerName: String,
    val agentName: String,
    val accidentRelatedNotes: String,
)
