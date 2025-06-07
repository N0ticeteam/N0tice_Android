package com.example.n0tice.core.auth

import android.content.Context
import android.content.SharedPreferences
import com.example.n0tice.BuildConfig
import com.example.n0tice.core.api.sgis.SgisApiService
import com.example.n0tice.core.api.sgis.SgisClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// 토큰 생성 및 sp에 저장하여 관리
class SgisAccessTokenManager(context: Context) {
    private val PREFS_NAME = "sgis_prefs"
    private val ACCESS_TOKEN = "access_token"
    private val ACCESS_TIMEOUT = "access_timeout"

    // 토큰을 저장할 sp 생성
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // BuildConfig에서 가져오기
    private val CONSUMER_KEY = BuildConfig.CONSUMER_KEY
    private val CONSUMER_SECRET = BuildConfig.CONSUMER_SECRET

    private val service = SgisClient.getInstance().create(SgisApiService::class.java)

    private var cachedToken: String? = null
    private var cachedTimeout: Long = 0L

    init {
        cachedToken = sharedPrefs.getString(ACCESS_TOKEN, null)
        cachedTimeout = sharedPrefs.getLong(ACCESS_TIMEOUT, 0L)
    }

    // 현재 가지고 있는 토큰을 반환한다
    fun getCurrentAccessToken(): String? {
        return cachedToken
    }

    // 토큰 발급 함수: 실패 시 null을 반환한다
    suspend fun fetchAndStoreNewAccessToken(): String? {
        return withContext(Dispatchers.IO) {
            try {
                val authResponse = service.getAccessToken(CONSUMER_KEY, CONSUMER_SECRET)

                // 요청 성공 시
                if (authResponse.errCd == 0 && authResponse.errMsg == "Success") {
                    val newToken = authResponse.result.accessToken
                    val newTimeout = authResponse.result.accessTimeout

                    // 새로운 토큰과 만료기한을 sp에 저장한다
                    sharedPrefs.edit().apply {
                        putString(ACCESS_TOKEN, newToken)
                        putLong(ACCESS_TIMEOUT, newTimeout)
                        apply()
                    }

                    cachedToken = newToken
                    cachedTimeout = newTimeout

                    println("Access Token successfully refreshed.")
                    newToken
                } else {
                    println("Failed to get new Access Token: ${authResponse.errMsg}")
                    null
                }
            } catch (e: Exception) {
                println("Error fetching new Access Token: ${e.message}")
                null
            }
        }
    }

    // 토큰이 유효한지 확인하는 함수
    fun isAccessTokenValid(): Boolean {
        // cachedToken이 null이 아니고 현재 시간이 캐시된 만료 시간보다 작을 때 (만료 전일 때)
        return cachedToken != null && System.currentTimeMillis() < cachedTimeout
    }
}