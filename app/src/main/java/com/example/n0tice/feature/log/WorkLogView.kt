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
import com.example.n0tice.core.api.n0tice.dto.WorkLog
import com.example.n0tice.core.ui.theme.BlueGray
import com.example.n0tice.core.ui.theme.Navy
import com.example.n0tice.core.ui.theme.SGreen
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun WorkLogView(
    showLogReadSheet: Boolean,
    log: WorkLog
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {})
            .drawWithContent {
                val paddingPx = with(density) { 10.dp.toPx() }
                clipRect(
                    top = -paddingPx,
                    left = -paddingPx,
                    right = size.width + paddingPx,
                    bottom = size.height + paddingPx
                ) { this@drawWithContent.drawContent() }
            }
    ) {
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
                    text = "${log.startTime} - ${log.endTime}",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    color = BlueGray
                )
            }

            Text(
                text = log.title,
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
