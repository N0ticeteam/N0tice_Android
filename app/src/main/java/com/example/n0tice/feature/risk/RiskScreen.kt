package com.example.n0tice.feature.risk

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.Surface
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
import androidx.navigation.NavController
import com.example.n0tice.core.ui.theme.Gray
import com.example.n0tice.core.ui.theme.preFontFamily
import com.example.n0tice.feature.risk.address.AddrSearchbar

@Composable
fun RiskScreen(
    riskViewModel: RiskViewModel,
    navController: NavController,
) {
    val companyList = riskViewModel.companyList.collectAsState().value

    // 화면 진입 시 초기화
    LaunchedEffect(Unit) {
        riskViewModel.clearCompanyList()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {

            Text(
                text = "고위험 사업장 찾기",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                ),
                color = Color.Black
            )

            CompanySearchbar(
                search = { riskViewModel.getCompanyByKeyword(it) }
            )

            AddrSearchbar(
                navigateToSearch = { navController.navigate("addr_search") },
            )

            Divider()

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RiskCompanyHeader()

                if (companyList.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "검색 결과가 존재하지 않습니다",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            ),
                            textAlign = TextAlign.Center,
                            color = Gray
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(companyList) { company ->
                            RiskCompanyItem(company)

                        }
                    }
                }
            }
        }
    }
}
