package com.example.n0tice.core.api.n0tice.dto

data class AccidentCase(
    val title: String,
    val courtName: String,
    val caseNumber: String
)

data class AccidentCaseDetail(
    val title: String,
    val content: String
)
