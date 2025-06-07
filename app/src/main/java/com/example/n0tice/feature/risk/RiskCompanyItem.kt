package com.example.n0tice.feature.risk

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.api.n0tice.dto.Company
import com.example.n0tice.core.ui.theme.BackgroundGray
import com.example.n0tice.core.ui.theme.DeathRed
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun RiskCompanyItem(result: Company) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundGray, RoundedCornerShape(12.dp)),
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 20.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(0.15f)
                    .background(DeathRed, RoundedCornerShape(10.dp))
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp),
                    text = result.accidentType,
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }

            Text(
                modifier = Modifier.weight(0.25f),
                text = result.dataYear.toString(),
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .weight(0.25f)
                    .padding(horizontal = 4.dp),
                text = result.companyName,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .weight(0.35f)
                    .padding(horizontal = 8.dp),
                text = result.address,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}
