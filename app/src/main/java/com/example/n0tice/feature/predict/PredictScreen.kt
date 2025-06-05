package com.example.n0tice.feature.predict

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.n0tice.R
import com.example.n0tice.core.ui.theme.SubGreen
import com.example.n0tice.core.ui.theme.FGreen
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun PredictScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 15.dp, end = 15.dp, top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(170.dp),
            ){
                Image(
                    modifier = Modifier.size(110.dp).align(Alignment.BottomStart),
                    painter = painterResource(R.drawable.left_cloud),
                    contentDescription = null
                )

                Image(
                    modifier = Modifier.size(110.dp).align(Alignment.TopEnd),
                    painter = painterResource(R.drawable.right_cloud),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "나와 비슷한 상황에서의\n패소 사례 살펴보기",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FGreen, RoundedCornerShape(16.dp)).padding(vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "유사한 상황에서 다른 사람들은 어떤 판단을 받았을까요?",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    ),
                    color = Color.Black
                )

                Text(
                    text = "실제 패소 사례를 통해 내 사건에 어떤 점이\n리하거나 불리할 수 있는지 미리 확인해보세요",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp
                    ),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            }

            TextButton(
                modifier = Modifier
                    .padding(top=20.dp)
                    .background(color = SubGreen, shape = RoundedCornerShape(20.dp)),
                onClick = { navController.navigate("scenario") }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 45.dp),
                    text = "시작하기",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    color = Color.White
                )
            }
        }
    }
}
