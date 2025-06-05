package com.example.n0tice.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.R
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier.padding(bottom = 20.dp),
    onBackPressed: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(40.dp)
                .padding(start = 10.dp),
            onClick = { onBackPressed() }
        ) {
            Icon(
                painter = painterResource(R.drawable.btn_back),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = title,
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            ),
            color = Color.Black
        )
    }
}