package com.example.n0tice.core.api.sgis

import com.example.n0tice.core.api.sgis.dto.AddrStageResponse
import com.example.n0tice.core.api.sgis.dto.AuthResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SgisApiService {
    // Access Token 발급 API
    @GET("OpenAPI3/auth/authentication.json")
    suspend fun getAccessToken(
        @Query("consumer_key") consumerKey: String,
        @Query("consumer_secret") consumerSecret: String
    ): AuthResponse

    // 단계별 주소 조회 API
    // 최상위(시/도) 목록을 가져올 때
    @GET("OpenAPI3/addr/stage.json")
    suspend fun getTopLevelAddresses(
        @Query("accessToken") accessToken: String
    ): AddrStageResponse

    // 하위(시/군/구, 읍/면/동) 목록을 가져올 때
    @GET("OpenAPI3/addr/stage.json")
    suspend fun getSubLevelAddresses(
        @Query("accessToken") accessToken: String,
        @Query("cd") cd: String
    ): AddrStageResponse
}