package com.example.n0tice.core.api.n0tice.dto

data class N0ticeResponse<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val data: T,
)