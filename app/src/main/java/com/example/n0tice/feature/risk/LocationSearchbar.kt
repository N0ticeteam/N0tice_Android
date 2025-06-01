package com.example.n0tice.feature.risk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.R
import com.example.n0tice.core.ui.theme.LightGreen
import com.example.n0tice.core.ui.theme.LocationTextGray
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun LocationSearchbar(keyword: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "지역명",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                color = Color.Black
            )

            Text(
                text = "으로 찾고 싶어요",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                color = Color.Black
            )
        }

        TextField(
            value = keyword.value,
            onValueChange = { keyword.value = it },
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(20.dp),
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = null,
                    tint = LocationTextGray
                )
            },
            placeholder = {
                Text(
                    text = "지역명을 입력하세요",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    color = LocationTextGray
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGreen,
                unfocusedContainerColor = LightGreen,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            )
        )
    }
}