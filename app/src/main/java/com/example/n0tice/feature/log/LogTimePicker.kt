package com.example.n0tice.feature.log

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.n0tice.core.ui.theme.preFontFamily
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogTimePicker(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(
                    top = 35.dp,
                    start = 10.dp,
                    end = 10.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    timeSelectorSelectedContainerColor = Color.White,
                    timeSelectorSelectedContentColor = Color.Black,
                    timeSelectorUnselectedContainerColor = Color.White,
                    timeSelectorUnselectedContentColor = Color.Black,

                    )
            )

            Divider(color = Color.LightGray, thickness = 1.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = "취소",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                        ),
                        color = Color.Black
                    )
                }

                // 버튼 사이 구분선
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .width(1.dp)
                        .height(45.dp)
                )

                Button(
                    onClick = {
                        onConfirm("${timePickerState.hour}:${timePickerState.minute}")
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = "확인",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                        ),
                        color = Color.Black
                    )
                }
            }
        }
    }
}
