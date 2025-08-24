package com.example.n0tice.core.api.n0tice

import com.example.n0tice.core.api.n0tice.dto.AccidentCase
import com.example.n0tice.core.api.n0tice.dto.AccidentCaseDetail
import com.example.n0tice.core.api.n0tice.dto.Company
import com.example.n0tice.core.api.n0tice.dto.DailyWorkLog
import com.example.n0tice.core.api.n0tice.dto.MonthlyWorkLog
import com.example.n0tice.core.api.n0tice.dto.N0ticeResponse
import com.example.n0tice.core.api.n0tice.dto.UserSituation
import com.example.n0tice.core.api.n0tice.dto.UserSituationRequest
import com.example.n0tice.core.api.n0tice.dto.WorkLogRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface N0ticeApiService {

    // 일지 작성 API
    @POST("api/work-logs")
    suspend fun createWorkLog(
        @Body req: WorkLogRequest
    ): Response<N0ticeResponse<Unit>>

    // 특정 일지 조회 API
    @GET("api/work-logs")
    suspend fun readWorkLog(
        @Query("date") date: String,
        @Query("userId") userId: String
    ): Response<N0ticeResponse<DailyWorkLog>>

    // 월간 일지 조회 API
    @GET("api/work-logs/monthly")
    suspend fun readMonthlyWorkLogs(
        @Query("year") year: String,
        @Query("month") month: String,
        @Query("userId") userId: String,
    ): Response<N0ticeResponse<List<MonthlyWorkLog>>>

    // 키워드 기반 고위험 사업장 조회 API
    @GET("api/companies/search")
    suspend fun getCompanyByKeyword(
        @Query("keyword") keyword: String,
    ):Response<N0ticeResponse<List<Company>>>

    // 주소(시/구/동) 기반 고위험 사업장 조회 API
    @GET("api/companies/location")
    suspend fun getCompanyByAddress(
        @Query("city") city: String,
        @Query("district") district: String,
        @Query("neighborhood") neighborhood: String
    ):Response<N0ticeResponse<List<Company>>>

    // 사용자 상황 입력 (kindb/kindc)
    @POST("api/situation-input")
    suspend fun setUserSituation(
        @Body req: UserSituationRequest
    ):Response<N0ticeResponse<UserSituation>>

    // 유사 산재 판례 매칭 결과 조회
    @GET("api/accident-cases/match")
    suspend fun getAccidentCases(
        @Query("input-id") input_id: Int,
    ):Response<N0ticeResponse<List<AccidentCase>>>

    // 판례 상세 조회
    @GET("api/accident-cases/detail")
    suspend fun getAccidentCaseDetail(
        @Query("input-id") input_id: Int,
        @Query("case-number") case_number: String
    ):Response<N0ticeResponse<AccidentCaseDetail>>

}