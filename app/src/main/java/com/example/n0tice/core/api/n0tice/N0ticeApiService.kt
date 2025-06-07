package com.example.n0tice.core.api.n0tice

import com.example.n0tice.core.api.n0tice.dto.DailyWorkLog
import com.example.n0tice.core.api.n0tice.dto.MonthlyWorkLog
import com.example.n0tice.core.api.n0tice.dto.N0ticeResponse
import com.example.n0tice.core.api.n0tice.dto.WorkLogRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface N0ticeApiService {

    // 일지 작성 API
    @POST("api/work-logs")
    suspend fun createWorkLog(
        @Body req: WorkLogRequest
    ): N0ticeResponse<Unit>

    // 특정 일지 조회 API
    @GET("api/work-logs")
    suspend fun readWorkLog(
        @Query("date") date: String,
        @Query("userId") userId: Long
    ): N0ticeResponse<DailyWorkLog>

    @GET("api/work-logs/monthly")
    suspend fun readMonthlyWorkLogs(
        @Query("year") year: String,
        @Query("month") month: String,
        @Query("userId") userId: Long,
    ): N0ticeResponse<MonthlyWorkLog>
}