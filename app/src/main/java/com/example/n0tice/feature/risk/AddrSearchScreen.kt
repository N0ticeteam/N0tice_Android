package com.example.n0tice.feature.risk

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.R
import com.example.n0tice.core.components.TopBar
import com.example.n0tice.core.api.sgis.dto.AddrStageItem
import com.example.n0tice.core.ui.theme.LightGreen
import com.example.n0tice.core.ui.theme.LocationFinal
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun AddrSearchScreen(
    viewModel: RiskViewModel,
    onBackPressed: () -> Unit
) {
    val addrState = viewModel.addrState.collectAsState().value
    val currentStage = viewModel.currentStage.collectAsState().value

    val selectedSi = viewModel.addrState.collectAsState().value.selectedSi
    val selectedGu = viewModel.addrState.collectAsState().value.selectedGu
    val selectedDong = viewModel.addrState.collectAsState().value.selectedDong

    var showBtn by remember { mutableStateOf(false) }

    val address = listOfNotNull(
        selectedSi?.addr_name,
        selectedGu?.addr_name,
        selectedDong?.addr_name
    ).joinToString(" ")

    LaunchedEffect(Unit) {
        viewModel.reset()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(
            title = "지역 선택",
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
            onBackPressed = onBackPressed
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(56.dp)
                .background(LightGreen)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (currentStage != AddrStage.SI) showBtn = true else showBtn = false
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showBtn) {
                    IconButton(
                        modifier = Modifier
                            .size(40.dp),
                        onClick = {
                            when (currentStage) {
                                AddrStage.GU -> viewModel.backToSiStage()
                                AddrStage.DONG -> viewModel.backToGuStage()
                                else -> {}
                            }

                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.btn_left_arrow),
                            contentDescription = null
                        )
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(start = 12.dp),
                    text = address,
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    color = Color.Black
                )
            }

            if (showBtn) {
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 15.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = "확인",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        ),
                        color = Color.Black
                    )
                }
            }
        }

        Row {
            // 현재 단계
            LazyColumn(
                modifier = Modifier.weight(0.4f)
            ) {
                when (currentStage) {
                    AddrStage.SI -> {
                        // SI 단계: 시/도 리스트 표시
                        items(addrState.siList) { si ->
                            val isSelected = si == selectedSi
                            AddressListItem(
                                item = si,
                                backgroundColor = if (isSelected) LightGreen else Color.White,
                                onClick = { viewModel.selectSi(si) }
                            )
                        }
                    }

                    AddrStage.GU -> {
                        // GU 단계: 구 리스트 표시
                        items(addrState.guList) { gu ->
                            val isSelected = gu == selectedGu
                            AddressListItem(
                                item = gu,
                                backgroundColor = if (isSelected) LightGreen else Color.White,
                                onClick = { viewModel.selectGu(gu) }
                            )
                        }
                    }

                    AddrStage.DONG -> {
                        // DONG 단계: 구 리스트 표시 (선택된 구 표시)
                        items(addrState.guList) { gu ->
                            val isSelected = gu == selectedGu
                            AddressListItem(
                                item = gu,
                                backgroundColor = if (isSelected) LightGreen else Color.White,
                                onClick = {
                                    // 이미 선택된 구이므로 클릭해도 변경 없음
                                    // 또는 다른 구 선택 시 해당 구의 동 리스트로 변경
                                    if (gu != selectedGu) {
                                        viewModel.selectGu(gu)
                                    }
                                }
                            )
                        }
                    }
                }
            }

            // 다음 단계
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.6f)
                    .background(LightGreen)
            ) {
                when (currentStage) {
                    AddrStage.SI -> {
                        // SI 단계: 선택된 시의 구 리스트 표시
                        if (selectedSi != null) {
                            items(addrState.guList) { gu ->
                                val isSelected = gu == selectedGu // 선택된 구 확인
                                AddressListItem(
                                    item = gu,
                                    backgroundColor = if (isSelected) LocationFinal else LightGreen,
                                    onClick = { viewModel.selectGu(gu) }
                                )
                            }
                        }
                    }

                    AddrStage.GU -> {
                        // GU 단계: 선택된 구의 동 리스트 표시
                        if (selectedGu != null) {
                            items(addrState.dongList) { dong ->
                                val isSelected = dong == selectedDong
                                AddressListItem(
                                    item = dong,
                                    backgroundColor = if (isSelected) LocationFinal else LightGreen,
                                    onClick = { viewModel.selectDong(dong) }
                                )
                            }
                        }
                    }

                    AddrStage.DONG -> {
                        // DONG 단계: 동 리스트 표시
                        items(addrState.dongList) { dong ->
                            val isSelected = dong == selectedDong
                            AddressListItem(
                                item = dong,
                                backgroundColor = if (isSelected) LocationFinal else LightGreen,
                                onClick = { viewModel.selectDong(dong) }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AddressListItem(
    item: AddrStageItem,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(vertical = 16.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = item.addr_name,
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            color = Color.Black
        )
    }
}