package com.example.n0tice.core.api.sgis.dto

data class AddrStageResponse(
    val id: String,
    val result: List<AddrStageItem>
)

data class AddrStageItem(
    val y_coor: String,
    val full_addr: String,
    val x_coor: String,
    val addr_name: String,
    val cd: String
)