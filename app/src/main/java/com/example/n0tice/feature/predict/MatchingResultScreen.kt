package com.example.n0tice.feature.predict

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.R
import com.example.n0tice.core.components.TopBar
import com.example.n0tice.core.ui.theme.DeathRed
import com.example.n0tice.core.ui.theme.LightRed
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun MatchingResultScreen(
    onBackPressed: () -> Unit,
    navigateToLoss: () -> Unit
) {
    val options = listOf(
        "상황:" to "질병",
        "추가 정보:" to "사업주가 산재 신청을 거부했어요",
        "추가 키워드:" to "목격자 없음"
    )

    val list = listOf(
        "출퇴근 중 교통사고 관련 산재 인정 여부",
        "출퇴근 중 교통사고 관련 산재 인정 여부",
        "출퇴근 중 교통사고 관련 산재 인정 여부",
        "출퇴근 중 교통사고 관련 산재 인정 여부",
        "출퇴근 중 교통사고 관련 산재 인정 여부"
    )

    var expanded by remember { mutableStateOf(false) }
    val sort = listOf("정확도순", "최신순", "조회순")
    var selectedOption by remember { mutableStateOf(sort[0]) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBar(
                title = "사례 매칭 결과",
                onBackPressed = onBackPressed
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            ) {
                // 사용자 상황 카드
                UserCaseCard(options, navigateToLoss)

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedOption,
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        )

                        IconButton(
                            onClick = { expanded = !expanded },
                        ) {
                            Icon(
                                painter = if (expanded) painterResource(R.drawable.arrow_drop_up)
                                else painterResource(R.drawable.arrow_drop_down),
                                contentDescription = null
                            )
                        }

                        DropdownMenu(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(end = 25.dp),
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            sort.forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = option,
                                            style = TextStyle(
                                                fontFamily = preFontFamily,
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 12.sp
                                            ),
                                            textAlign = TextAlign.End
                                        )
                                    },
                                    onClick = {
                                        selectedOption = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                }

                LazyColumn {
                    items(list) {
                        Surface(
                            shape = RoundedCornerShape(14.dp), // 2
                            color = Color.White, // 3
                            shadowElevation = 6.dp, // 4
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .fillMaxWidth() // 5
                                .drawWithContent {
                                    val paddingPx = with(density) { 10.dp.toPx() }
                                    clipRect(
                                        top = 0f, // 7
                                        left = -paddingPx, // 8
                                        right = size.width + paddingPx, // 9
                                        bottom = size.height + paddingPx // 10
                                    ) { this@drawWithContent.drawContent() }
                                }
                        ) {
                            Text(
                                text = it,
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.padding(20.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UserCaseCard(options: List<Pair<String, String>>, navigateToLoss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.8.dp, DeathRed, RoundedCornerShape(14.dp))
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterStart),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            options.forEach {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = it.first,
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        ),
                        color = Color.Black
                    )
                    Box(
                        modifier = Modifier.background(
                            LightRed,
                            RoundedCornerShape(13.dp)
                        ),
                    )
                    {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = it.second,
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            color = Color.Black
                        )
                    }
                }
            }
        }
        TextButton(
            modifier = Modifier
                .heightIn(min = 100.dp)
                .align(Alignment.CenterEnd),
            onClick = { navigateToLoss() },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DeathRed
            )
        ) {
            Text(
                text = "패소율\n" +
                        "예측하기",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                ),
                modifier = Modifier.padding(horizontal = 4.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}