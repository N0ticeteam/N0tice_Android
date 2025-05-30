@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.n0tice.feature.log

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.n0tice.R
import com.example.n0tice.core.components.LogDatePicker
import com.example.n0tice.core.components.LogTimePicker
import com.example.n0tice.core.ui.theme.BlueGray
import com.example.n0tice.core.ui.theme.LightGray
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.preFontFamily
import java.time.LocalDate

@Composable
fun LogScreen(
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    val title = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val date = remember { mutableStateOf(LocalDate.now().toString()) }
    val supervisor = remember { mutableStateOf("") }
    val deputy = remember { mutableStateOf("") }
    val notes = remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }

    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }

    // 날짜는 스트링으로 관리
    val selectedDate = remember { mutableStateOf(LocalDate.now().toString()) }

    val startTime = remember { mutableStateOf("") }
    val endTime = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 25.dp, start = 15.dp, end = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LogCalendarView(
                selectedDate = selectedDate.value,
                onDateSelected = { selectedDate.value = it }
            )

            // 일지 작성 버튼
            Box(
                modifier = Modifier.padding(bottom = 35.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = MainGreen,
                            shape = CircleShape
                        ),
                    onClick = { showBottomSheet = true },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            // 일지 작성 시트
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    dragHandle = null,
                    windowInsets = WindowInsets(0), // 시스템 인셋 무시
                    containerColor = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 30.dp, horizontal = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(
                            text = "일지 작성하기",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.5.dp,
                                    LightGray,
                                    RoundedCornerShape(10.dp)
                                ),
                            value = title.value,
                            onValueChange = { title.value = it },
                            placeholder = {
                                Text(
                                    text = "제목 *",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    ),
                                    color = BlueGray
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White
                            )
                        )

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.5.dp,
                                    LightGray,
                                    RoundedCornerShape(10.dp)
                                )
                                .height(100.dp),
                            value = content.value,
                            onValueChange = { content.value = it },
                            placeholder = {
                                Text(
                                    text = "내용을 입력해주세요",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    ),
                                    color = BlueGray
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White
                            )
                        )

                        // 날짜 입력
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.5.dp,
                                    LightGray,
                                    RoundedCornerShape(10.dp)
                                ),
                            value = selectedDate.value,
                            onValueChange = { },
                            trailingIcon = {
                                IconButton(
                                    onClick = { showDatePicker = !showDatePicker }
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.calendar),
                                        contentDescription = null,
                                        tint = BlueGray
                                    )
                                }
                            },
                            placeholder = {
                                Text(
                                    text = "날짜",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    ),
                                    color = BlueGray
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White
                            )
                        )

                        // 시작 시간 입력
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            TextField(
                                modifier = Modifier
                                    .weight(1f)
                                    .border(
                                        1.5.dp,
                                        LightGray,
                                        RoundedCornerShape(16.dp)
                                    ),
                                value = startTime.value,
                                onValueChange = { },
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            showStartTimePicker = true
                                        }
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.clock),
                                            contentDescription = null,
                                            tint = BlueGray
                                        )
                                    }
                                },
                                placeholder = {
                                    Text(
                                        text = "시작 시간",
                                        style = TextStyle(
                                            fontFamily = preFontFamily,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp
                                        ),
                                        color = BlueGray
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White
                                )
                            )

                            // 종료 시간 입력
                            TextField(
                                modifier = Modifier
                                    .weight(1f)
                                    .border(
                                        1.5.dp,
                                        LightGray,
                                        RoundedCornerShape(10.dp)
                                    ),
                                value = endTime.value,
                                onValueChange = { },
                                trailingIcon = {
                                    IconButton(
                                        onClick = { showEndTimePicker = !showEndTimePicker }

                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.clock),
                                            contentDescription = null,
                                            tint = BlueGray
                                        )
                                    }
                                },
                                placeholder = {
                                    Text(
                                        text = "종료 시간",
                                        style = TextStyle(
                                            fontFamily = preFontFamily,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp
                                        ),
                                        color = BlueGray
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White
                                )
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            TextField(
                                modifier = Modifier
                                    .weight(1f)
                                    .border(
                                        1.5.dp,
                                        LightGray,
                                        RoundedCornerShape(16.dp)
                                    ),
                                value = supervisor.value,
                                onValueChange = {
                                    supervisor.value = it
                                },
                                placeholder = {
                                    Text(
                                        text = "현장 감독관",
                                        style = TextStyle(
                                            fontFamily = preFontFamily,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp
                                        ),
                                        color = BlueGray
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White
                                )
                            )

                            TextField(
                                modifier = Modifier
                                    .weight(1f)
                                    .border(
                                        1.5.dp,
                                        LightGray,
                                        RoundedCornerShape(10.dp)
                                    ),
                                value = deputy.value,
                                onValueChange = {
                                    deputy.value = it
                                },
                                placeholder = {
                                    Text(
                                        text = "현장 대리인",
                                        style = TextStyle(
                                            fontFamily = preFontFamily,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp
                                        ),
                                        color = BlueGray
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White
                                )
                            )
                        }

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.5.dp,
                                    LightGray,
                                    RoundedCornerShape(10.dp)
                                )
                                .height(100.dp),
                            value = content.value,
                            onValueChange = { content.value = it },
                            placeholder = {
                                Text(
                                    text = "산재 특이사항",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    ),
                                    color = BlueGray
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White
                            )
                        )

                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MainGreen, shape = RoundedCornerShape(10.dp)),
                            onClick = {
                                // api 요청 로직 작성

                                showBottomSheet = false
                            }

                        ) {
                            Text(
                                text = "일지 작성하기",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                ),
                                color = Color.White
                            )
                        }
                    }
                }

                if (showDatePicker) {
                    LogDatePicker(
                        onDateSelected = { dateStr ->
                            selectedDate.value = dateStr ?: selectedDate.value
                        },
                        onDismiss = { showDatePicker = false }
                    )
                }

                if (showStartTimePicker) {
                    LogTimePicker(
                        onConfirm = { time ->
                            startTime.value = time
                        },
                        onDismiss = { showStartTimePicker = false }
                    )
                }

                if (showEndTimePicker) {
                    LogTimePicker(
                        onConfirm = { time ->
                            endTime.value = time
                        },
                        onDismiss = { showEndTimePicker = false }
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun LogPreview() {
    LogScreen(rememberNavController())
}
