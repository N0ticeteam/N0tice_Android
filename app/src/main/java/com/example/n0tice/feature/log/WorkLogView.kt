package com.example.n0tice.feature.log

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.api.n0tice.dto.DailyWorkLog
import com.example.n0tice.core.ui.theme.BlueGray
import com.example.n0tice.core.ui.theme.SGreen
import com.example.n0tice.core.ui.theme.preFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkLogView(
    log: DailyWorkLog,
) {
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(log) {
        expanded = false
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 6.dp,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .clickable(onClick = { expanded = !expanded })
            .drawWithContent {
                val paddingPx = with(density) { 10.dp.toPx() }
                clipRect(
                    top = -paddingPx, // 7
                    left = -paddingPx, // 8
                    right = size.width + paddingPx, // 9
                    bottom = size.height + paddingPx // 10
                ) { this@drawWithContent.drawContent() }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .border(3.dp, SGreen, CircleShape)
                )

                Text(
                    text = "${log.startTime} - ${log.endTime}",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    ),
                    color = BlueGray
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                text = log.title,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            )

            if (expanded) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = log.content,
                        textStyle = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        onValueChange = { },
                        singleLine = true,
                        label = {
                            Text(
                                text = "내용",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                ),
                                color = BlueGray
                            )
                        },
                        readOnly = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = BlueGray,
                            unfocusedBorderColor = BlueGray

                        )
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(25.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = log.managerName,
                            textStyle = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            ),
                            onValueChange = { },
                            singleLine = true,
                            label = {
                                Text(
                                    text = "현장 감독관",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp
                                    ),
                                    color = BlueGray
                                )
                            },
                            readOnly = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = BlueGray,
                                unfocusedBorderColor = BlueGray

                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = log.agentName,
                            textStyle = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            ),
                            onValueChange = { },
                            singleLine = true,
                            label = {
                                Text(
                                    text = "현장 대리인",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp
                                    ),
                                    color = BlueGray
                                )
                            },
                            readOnly = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = BlueGray,
                                unfocusedBorderColor = BlueGray

                            )
                        )
                    }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = log.accidentRelatedNotes,
                        textStyle = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        onValueChange = { },
                        singleLine = true,
                        label = {
                            Text(
                                text = "현장 감독관",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                ),
                                color = BlueGray
                            )
                        },
                        readOnly = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = BlueGray,
                            unfocusedBorderColor = BlueGray,

                            )
                    )
                }
            }
        }
    }
}
