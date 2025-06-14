package com.example.n0tice

import android.app.Application
import com.navercorp.nid.NaverIdLoginSDK

class N0ticeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val clientId = BuildConfig.OAUTH_NAVER_CLIENT_ID
        val clientSecret = BuildConfig.OAUTH_NAVER_CLIENT_SECRET
        val clientName = BuildConfig.OAUTH_NAVER_CLIENT_NAME

        // 네이버 로그인 초기화
        NaverIdLoginSDK.initialize(
            context = this,
            clientId = clientId,
            clientSecret = clientSecret,
            clientName = clientName
        )
    }
}
