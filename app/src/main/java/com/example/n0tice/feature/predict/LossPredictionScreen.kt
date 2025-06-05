package com.example.n0tice.feature.predict

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.ui.theme.LightGreen
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.MainRed
import com.example.n0tice.core.ui.theme.RiskRed
import com.example.n0tice.core.ui.theme.SubGreen
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun LossPredictionScreen(
    navigateToResult: () -> Unit
) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenHeight = maxHeight
        val headerHeight = screenHeight * 0.28f
        val cardOffset = headerHeight - 40.dp

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGreen),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .background(
                        MainGreen,
                        RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "현재 사용자님의 상황은...",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        ),
                        color = Color.White
                    )
                    val options = listOf("질병", "사업주가 산재 신청을 거부했어요", "목격자 없음", "초진일이 늦었음")

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        options.chunked(2).forEach { rowItems ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                rowItems.forEach {
                                    Box(
                                        modifier = Modifier
                                            .background(LightGreen, RoundedCornerShape(14.dp))
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(
                                                vertical = 10.dp,
                                                horizontal = 14.dp
                                            ),
                                            text = it,
                                            style = TextStyle(
                                                fontFamily = preFontFamily,
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 13.sp
                                            ),
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.padding(top = cardOffset, start = 25.dp, end = 25.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    val total = 83
                    Column(
                        modifier = Modifier.padding(vertical = 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = "비슷한 사례 ${total}건 중",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            ),
                            color = Color.Black
                        )

                        Text(
                            text = "36건",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 38.sp
                            ),
                            color = MainGreen
                        )

                        Text(
                            text = "이 패소한 것으로 확인됐어요",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            ),
                            color = Color.Black
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = "AI가 예측한 내 패소 확률은?",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            ),
                            color = Color.Black
                        )

                        Text(
                            text = "77.6%",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 38.sp
                            ),
                            color = MainRed
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .background(RiskRed, RoundedCornerShape(24.dp)),
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = "\uD83D\uDD34 “흠… 조심할 필요가 있어요.”",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 13.sp
                                    ),
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )

                                Text(
                                    text = "패소 가능성이 높게 예측됐어요.",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp
                                    ),
                                )

                                Text(
                                    text = "중요한 쟁점이 빠졌는지 한번 점검해보면 좋아요.",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp
                                    ),
                                )

                            }

                        }
                    }
                }
            }

            TextButton(
                modifier = Modifier
                    .padding(bottom = 45.dp)
                    .background(color = SubGreen, shape = RoundedCornerShape(20.dp))
                    .align(Alignment.BottomCenter),
                onClick = { navigateToResult() }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
                    text = "사례 매칭 화면으로 돌아가기",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    color = Color.White
                )
            }
        }
    }
}