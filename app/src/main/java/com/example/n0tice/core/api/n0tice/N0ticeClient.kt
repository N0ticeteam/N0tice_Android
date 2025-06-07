package com.example.n0tice.core.api.n0tice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object N0ticeClient {
    private var instance: Retrofit? = null
    private const val BASE_URL = "http://54.180.45.192/"

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