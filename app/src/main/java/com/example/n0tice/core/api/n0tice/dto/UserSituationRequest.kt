package com.example.n0tice.core.api.n0tice.dto

data class UserSituationRequest(
    val userId: Int,
    val kindb: String,
    val kindc: String? // kindc는 nullable
)
