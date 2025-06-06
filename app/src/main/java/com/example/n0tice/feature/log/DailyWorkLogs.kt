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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.model.MonthlyLog
import com.example.n0tice.core.ui.theme.BlueGray
import com.example.n0tice.core.ui.theme.Navy
import com.example.n0tice.core.ui.theme.SGreen
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun DailyLog(
    showLogReadSheet: Boolean,
    log: List<MonthlyLog>
) {

    Surface(
        shape = RoundedCornerShape(16.dp), // 2
        color = Color.White, // 3
        shadowElevation = 4.dp, // 4
        modifier = Modifier
            .fillMaxWidth() // 5
            .clickable(onClick = {})
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
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .border(1.5.dp, LightGray, RoundedCornerShape(16.dp))
//            .clickable(
//                onClick = {
//                    showLogReadSheet1 = !showLogReadSheet1
//                }
//            )
//    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
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
                    text = "${log[0].startTime} - ${log[0].endTime}",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    color = BlueGray
                )
            }

            Text(
                text = log[0].title,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                color = Navy
            )
        }
    }
}
