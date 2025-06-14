package com.example.n0tice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.n0tice.core.ui.theme.N0ticeTheme
import com.example.n0tice.feature.login.LoginScreen
import com.example.n0tice.main.MainScreen
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var loginToken by remember { mutableStateOf<String?>(null) }

            N0ticeTheme {
                Surface {
                    if (loginToken != null) {
                        MainScreen()
                    } else {
                        LoginScreen(
                            startLogin = { onLoginSuccess ->
                                startNaverLogin(onLoginSuccess)
                            },
                            onLoginSuccess = { token ->
                                Log.d("Login", "get Token: $token")
                                loginToken = token
                            }
                        )
                    }
                }
            }
        }
    }

    fun startNaverLogin(onLoginSuccess: (String) -> Unit) {
        NaverIdLoginSDK.authenticate(this, object : OAuthLoginCallback {
            override fun onSuccess() {
                val accessToken = NaverIdLoginSDK.getAccessToken()
                Log.d("NaverLogin", "Access Token: $accessToken")

                runOnUiThread {
                    if (accessToken != null) {
                        onLoginSuccess(accessToken)
                    } // 로그인 성공 콜백 호출
                }
            }

            override fun onFailure(httpStatus: Int, message: String) {
                Log.e("NaverLogin", "Login failed: $message")
            }

            override fun onError(errorCode: Int, message: String) {
                Log.e("NaverLogin", "Login error: $message")
            }
        })
    }
}
