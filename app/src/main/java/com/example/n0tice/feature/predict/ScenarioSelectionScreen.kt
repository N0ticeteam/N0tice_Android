@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.n0tice.feature.predict

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.R
import com.example.n0tice.core.components.TopBar
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.SubGreen
import com.example.n0tice.core.ui.theme.WorkplaceSearchText
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun ScenarioSelectionScreen(onBackPressed: () -> Unit, navigateToResult: () -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    var information by remember { mutableStateOf("") }

    val options = listOf(
        "출퇴근 중 사고였어요",
        "장애인이에요",
        "일용직 또는 단기 계약직이에요",
        "5인 미만 사업장에서 일해요",
        "사업주가 산재 신청을 거부했어요",
        "실습생/현장교육 중 사고였어요"
    )

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
            TopBar(title = "산재 패소 사례 매칭", onBackPressed = onBackPressed)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    // Step 1
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        CaseStepSelector(
                            case = "상황 선택",
                            detail = "당신의 상황을 알려주세요\n비슷한 패소 사례를 찾기 위해 기본 상황을 먼저 선택해 주세요"
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CaseBox(
                                case1 = "사고",
                                ex1 = "작업 중 다친 경우 등",
                                case2 = "질병",
                                ex2 = "직무 관련 질병, 과로 등"
                            )

                            CaseBox(
                                case1 = "정신적 피해",
                                ex1 = "직장 내 괴롭힘, 성희롱, 감정노동 등",
                                case2 = "기타",
                                ex2 = "위에 해당하지 않는 경우"
                            )
                        }
                    }
                }

                // Step 2
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        CaseStepSelector(
                            case = "추가 정보",
                            detail = "해당하는 내용이 있으신가요?\n비슷한 사례를 더 정확하게 찾을 수 있어요."
                        )
                        ChipSelection(options, 2)
                    }
                }

                // Step 3
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        CaseStepSelector(
                            case = "추가 키워드",
                            detail = "추가로 입력하고 싶은 내용이 있으신가요?\n사고 경위나 기각 이유 등, 사례 검색에 도움이 될 수 있어요"
                        )

                        TextField(
                            value = "",
                            onValueChange = { },
                            placeholder = {
                                Text(
                                    text = "예시: 공상으로 처리됨 / 초진일이 늦었음 / 목격자 없음",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp
                                    ),
                                    color = WorkplaceSearchText
                                )
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                                .border(1.dp, Color.Black, RoundedCornerShape(14.dp)),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White
                            )
                        )
                    }
                }

                item {
                    TextButton(
                        modifier = Modifier
                            .padding(bottom = 35.dp)
                            .background(color = SubGreen, shape = RoundedCornerShape(20.dp)),
                        onClick = { navigateToResult() }
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 45.dp),
                            text = "패소 사례 분석 시작하기",
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
    }
}

@Composable
private fun CaseBox(case1: String, ex1: String, case2: String, ex2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Column(
            modifier = Modifier
                .border(1.8.dp, MainGreen, RoundedCornerShape(12.dp))
                .clickable(onClick = {})
                .padding(15.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Case(
                case = case1,
                ex = ex1
            )
        }

        Column(
            modifier = Modifier
                .border(1.8.dp, MainGreen, RoundedCornerShape(12.dp))
                .clickable(onClick = {})
                .padding(15.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Case(
                case = case2,
                ex = ex2
            )
        }

    }
}

@Composable
private fun Case(case: String, ex: String) {
    Icon(
        modifier = Modifier.padding(bottom = 6.dp),
        painter = painterResource(R.drawable.fall),
        contentDescription = null,
        tint = Color.Black
    )

    Text(
        text = case,
        style = TextStyle(
            fontFamily = preFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        color = Color.Black
    )

    Text(
        text = ex,
        style = TextStyle(
            fontFamily = preFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp
        ),
        color = Color.Black
    )
}

@Composable
private fun CaseStepSelector(case: String, detail: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            Modifier
                .height(70.dp)
                .width(3.dp)
                .background(MainGreen)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = case,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                color = Color.Black
            )

            Text(
                text = detail,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                color = Color.Black
            )
        }
    }
}