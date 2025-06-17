package com.example.n0tice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.n0tice.core.ui.theme.N0ticeTheme
import com.example.n0tice.feature.login.LoginScreen
import com.example.n0tice.main.MainScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback

class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleGoogleSignInResult(task)
        }
    }

    private var pendingGoogleLoginCallback: ((String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 구글 로그인 클라이언트 설정
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.OAUTH_GOOGLE_CLIENT_ID)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            var loginToken by remember { mutableStateOf<String?>(null) }

            N0ticeTheme {
                Surface {
                    if (loginToken != null) {
                        MainScreen()
                    } else {
                        LoginScreen(
                            startNaverLogin = { onLoginSuccess ->
                                startNaverLogin(onLoginSuccess)
                            },
                            startGoogleLogin = { onLoginSuccess ->
                                startGoogleLogin(onLoginSuccess)
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

    private fun startNaverLogin(onLoginSuccess: (String) -> Unit) {
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

    private fun startGoogleLogin(onLoginSuccess: (String) -> Unit) {
        pendingGoogleLoginCallback = onLoginSuccess
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account?.idToken
            Log.d("GoogleLogin", "ID Token: $idToken")

            if (idToken != null) {
                pendingGoogleLoginCallback?.invoke(idToken)
            }
        } catch (e: ApiException) {
            Log.e("GoogleLogin", "signInResult:failed code=" + e.statusCode)
        } finally {
            pendingGoogleLoginCallback = null
        }
    }
}