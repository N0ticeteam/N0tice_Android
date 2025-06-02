package com.example.n0tice.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SgisClient {
    private var instance: Retrofit? = null
    private const val BASE_URL = "https://sgisapi.kostat.go.kr/"

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}
