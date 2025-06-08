package com.example.n0tice.core.api.n0tice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object N0ticeClient {
    private var instance: Retrofit? = null
    private const val BASE_URL = "http://52.79.71.10:8080/"

    fun getInstance(): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // URL + 파라미터 + 응답 모두 출력
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}