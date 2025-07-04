package com.example.n0tice.feature.log

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.R
import com.example.n0tice.core.api.n0tice.dto.WorkLogRequest
import com.example.n0tice.core.ui.theme.BlueGray
import com.example.n0tice.core.ui.theme.LightGray
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun LogWriteSheet(
    selectedDate: String,
    startTime: String,
    endTime: String,
    openDatePicker: () -> Unit,
    openStartTimePicker: () -> Unit,
    openEndTimePicker: () -> Unit,
    close: (WorkLogRequest) -> Unit
) {
    val title = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val manager = remember { mutableStateOf("") }
    val agent = remember { mutableStateOf("") }
    val notes = remember { mutableStateOf("") }

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
                .heightIn(min = 100.dp),
            value = content.value,
            onValueChange = { content.value = it },
            placeholder = {
                Text(
                    text = "내용을 입력해주세요 *",
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
            value = selectedDate,
            onValueChange = { },
            trailingIcon = {
                IconButton(
                    onClick = { openDatePicker() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = null,
                        tint = BlueGray
                    )
                }
            },
            placeholder = {
                Text(
                    text = "날짜 *",
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
                value = startTime,
                onValueChange = { },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            openStartTimePicker()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_clock),
                            contentDescription = null,
                            tint = BlueGray
                        )
                    }
                },
                placeholder = {
                    Text(
                        text = "시작 시간 *",
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
                value = endTime,
                onValueChange = { },
                trailingIcon = {
                    IconButton(
                        onClick = { openEndTimePicker() }

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_clock),
                            contentDescription = null,
                            tint = BlueGray
                        )
                    }
                },
                placeholder = {
                    Text(
                        text = "종료 시간 *",
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
                value = manager.value,
                onValueChange = {
                    manager.value = it
                },
                placeholder = {
                    Text(
                        text = "현장 감독관 *",
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
                value = agent.value,
                onValueChange = {
                    agent.value = it
                },
                placeholder = {
                    Text(
                        text = "현장 대리인 *",
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
            value = notes.value,
            onValueChange = { notes.value = it },
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
                close(
                    WorkLogRequest(
                        userId = "1",
                        logDate = selectedDate,
                        title = title.value,
                        content = content.value,
                        startTime = startTime,
                        endTime = endTime,
                        managerName = manager.value,
                        agentName = agent.value,
                        accidentRelatedNotes = notes.value
                    )
                )
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
