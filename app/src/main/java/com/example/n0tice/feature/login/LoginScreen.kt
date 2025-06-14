package com.example.n0tice.feature.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.R
import com.example.n0tice.core.ui.theme.preFontFamily
import com.example.n0tice.core.ui.theme.yoonchildFamily

@Composable
fun LoginScreen(
    startLogin: ((String) -> Unit) -> Unit,
    onLoginSuccess: (String) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 40.dp, vertical = 50.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "N0tice ON!",
                style = TextStyle(
                    fontFamily = yoonchildFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                ),
                textAlign = TextAlign.Start,
                color = Color.Black
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "작업 기록은 스마트하게, 위험은 미리 체크",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                color = Color.Black
            )

            Image(
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 50.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = painterResource(R.drawable.img_login),
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF03C75A), RoundedCornerShape(35.dp))
                    .clickable(
                        onClick = {
                            startLogin { token ->
                                onLoginSuccess(token)
                            }
                        }
                    )
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    painter = painterResource(R.drawable.naver_login),
                    contentDescription = null
                )
            }

            Image(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .aspectRatio(6.5f)
                    .clickable(onClick = {
                        val testIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("n0tice://callback?accessToken=test_token")
                        )
                        context.startActivity(testIntent)

                    }),
                painter = painterResource(R.drawable.google_login),
                contentDescription = null
            )
        }
    }
}


@Preview
@Composable
fun Preview() {
    LoginScreen({}, {})
}