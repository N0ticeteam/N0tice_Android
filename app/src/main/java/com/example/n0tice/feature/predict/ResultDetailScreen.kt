package com.example.n0tice.feature.predict

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.components.TopBar
import com.example.n0tice.core.ui.theme.LightGray
import com.example.n0tice.core.ui.theme.MediumGray
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun ResultDetailScreen(
    predictViewModel: PredictViewModel,
    onBackPressed: () -> Unit,
    id: Int,
    caseNumber: String
) {
    val detail = predictViewModel.accidentCaseDetail.collectAsState().value

    LaunchedEffect(id, caseNumber) {
        predictViewModel.getAccidentCaseDetail(id, caseNumber)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom  = 20.dp)
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = "사례 매칭 조회",
            onBackPressed = onBackPressed
        )

        detail?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MediumGray, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = it.title,
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                // 화면 표시
                detail?.let {
                    Box(
                        modifier = Modifier
                            .background(LightGray, RoundedCornerShape(12.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = it.content,
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
    }
}