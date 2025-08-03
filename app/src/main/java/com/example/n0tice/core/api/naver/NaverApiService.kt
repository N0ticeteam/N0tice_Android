package com.example.n0tice.core.api.naver

import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object NaverApiService {
    fun fetchUserProfile(token: String): NaverUser {
        Log.d("Naver Login", "fetchUserProfile called")

        val apiUrl = "https://openapi.naver.com/v1/nid/me"
        val header = "Bearer $token"
        val requestHeaders = mapOf("Authorization" to header)

        val json = get(apiUrl, requestHeaders)

        // JSON 파싱
        val gson = Gson()
        val parsed = gson.fromJson(json, NaverUserResponse::class.java)

        Log.d("Naver Login", "User Profile: $parsed.response")

        return parsed.response
    }

    private fun get(apiUrl: String, requestHeaders: Map<String, String>): String {
        val connection = connect(apiUrl)
        return try {
            connection.requestMethod = "GET"
            for ((key, value) in requestHeaders) {
                connection.setRequestProperty(key, value)
            }

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                readBody(connection.inputStream)
            } else {
                readBody(connection.errorStream)
            }
        } finally {
            connection.disconnect()
        }
    }

    private fun connect(apiUrl: String): HttpURLConnection {
        val url = URL(apiUrl)
        return url.openConnection() as HttpURLConnection
    }

    private fun readBody(body: InputStream): String {
        val streamReader = InputStreamReader(body)
        BufferedReader(streamReader).use { reader ->
            val responseBody = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                responseBody.append(line)
            }
            return responseBody.toString()
        }
    }
}
