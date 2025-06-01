package com.example.n0tice.feature.risk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun RiskCompanyHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Text(
            modifier = Modifier.weight(0.15f),
            text = "분류",
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Text(
            modifier = Modifier.weight(0.25f),
            text = "발생연도",
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Text(
            modifier = Modifier.weight(0.25f),
            text = "사업장명",
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Text(
            modifier = Modifier.weight(0.35f),
            text = "사업장 소재지",
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center,
            color = Color.Black
        )

    }
}