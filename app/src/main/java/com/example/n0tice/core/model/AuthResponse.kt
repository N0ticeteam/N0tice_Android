package com.example.n0tice.core.model

data class AuthResponse(
    val id: String,
    val result: AuthResult,
    val errMsg: String,
    val errCd: Int,
    val trId: String
)

data class AuthResult(
    val accessTimeout: Long, // 유효 기간
    val accessToken: String
)