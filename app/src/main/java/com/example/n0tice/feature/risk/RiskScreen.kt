package com.example.n0tice.feature.risk

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.n0tice.core.model.Company
import com.example.n0tice.core.ui.theme.Gray
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun RiskScreen(
    navController: NavController,
) {
    val keyword = remember { mutableStateOf("") }

    val results = listOf(
        Company(
            id = 2,
            companyName = "청원미화재단청재단",
            dataYear = "2022",
            accidentType = "사망",
            address = "대전광역시 유성구 상대동 123"
        ),
        Company(
            id = 2,
            companyName = "S-OIL 공사",
            dataYear = "2023",
            accidentType = "부상",
            address = "대전 유성구 온천동 신공장 46"
        ),
        Company(
            id = 2,
            companyName = "(주)정민건설(S-Oil Offsite Improvement Plan공사중 [CON_4]원유토목기초공사)",
            dataYear = "2022",
            accidentType = "사망",
            address = "대전광역시 유성구 상대동 123"
        ),

        )

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

            CompanySearchbar(keyword)

            AddrSearchbar(
                navigateToSearch = { navController.navigate("addr_search") }
            )

            Divider()

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                RiskCompanyHeader()

                if (results == null) {
                    Text(
                        text = "검색 결과가 없어요",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        ),
                        color = Gray
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(results) { result ->
                            RiskCompanyItem(result)

                        }
                    }
                }
            }
        }
    }
}
