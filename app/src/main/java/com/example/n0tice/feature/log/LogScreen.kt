package com.example.n0tice.feature.log

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.n0tice.R
import com.example.n0tice.core.ui.theme.MainGreen

@Composable
fun LogScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 25.dp, horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LogCalendarView()

            Box(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = MainGreen,
                            shape = CircleShape
                        ),
                    onClick = {
                        // TODO: 클릭 시 bottom modal sheet

                    },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = null,
                        tint = Color.White
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
