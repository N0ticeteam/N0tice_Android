package com.example.n0tice.feature.predict

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.components.TopBar
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.SubGreen
import com.example.n0tice.core.ui.theme.preFontFamily
import com.example.n0tice.feature.predict.model.KindBMapping
import com.example.n0tice.feature.predict.model.KindBOption
import com.example.n0tice.feature.predict.model.KindCOption
import com.example.n0tice.feature.predict.model.Situation

@Composable
fun ScenarioSelectionScreen(
    predictViewModel: PredictViewModel,
    onBackPressed: () -> Unit,
    navigateToResult: () -> Unit
) {
    var selectedUserSituation by remember { mutableStateOf<Situation?>(null) }

    var selectedKindBOption by remember { mutableStateOf<KindBOption?>(null) }
    var selectedKindCOption by remember { mutableStateOf<KindCOption?>(null) }

    val currentKindBOptions: List<KindBOption> = remember(selectedUserSituation) {
        selectedUserSituation?.let { KindBMapping.fromSituation(it) } ?: emptyList()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBar(title = "산재 패소 사례 매칭", onBackPressed = onBackPressed)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    // Step 1
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        CaseStepSelector(
                            case = "상황 선택",
                            detail = "해당하는 내용이 있으신가요?\n유사한 패소 사례를 추천해드릴게요.\n"
                        )

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            SingleSelectChipGroup(
                                options = Situation.entries.toList(),
                                selectedOption = selectedUserSituation,
                                onOptionSelected = { selectedUserSituation = it },
                                labelFor = { it.label }
                            )
                        }
                    }
                }

                // Step 2
                item {
                    if (currentKindBOptions.isNotEmpty()) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            CaseStepSelector(
                                case = "추가 정보",
                                detail = "조금 더 구체적인 문제를 선택해주세요.\n정확한 사례 분석에 큰 도움이 됩니다."
                            )

                            SingleSelectChipGroup(
                                options = currentKindBOptions,
                                selectedOption = selectedKindBOption,
                                onOptionSelected = { selectedKindBOption = it },
                                labelFor = { it.label }
                            )
                        }
                    }
                }

                // Step 3
                item {
                    if (selectedKindBOption != null) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            CaseStepSelector(
                                case = "세부 상황",
                                detail = "세부적인 상황이 있다면 골라주세요.\n더 정밀한 사례를 추천받을 수 있어요."
                            )

                            SingleSelectChipGroup(
                                rows = 3,
                                options = KindCOption.entries.toList(),
                                selectedOption = selectedKindCOption,
                                onOptionSelected = { selectedKindCOption = it },
                                labelFor = { it.label }
                            )
                        }
                    }
                }

                item {
                    if (selectedKindBOption != null) {
                        TextButton(
                            modifier = Modifier
                                .padding(bottom = 35.dp)
                                .background(color = SubGreen, shape = RoundedCornerShape(20.dp)),
                            onClick = {
                                predictViewModel.inputUserSituation(
                                    "1", // TODO: 실제 아이디로 수정할 것
                                    selectedKindBOption!!, // if 문에서 걸러지므로 항상 not-null
                                    selectedKindCOption
                                )

                                navigateToResult()
                            }
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 45.dp),
                                text = "패소 사례 분석 시작하기",
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
        }
    }
}

@Composable
private fun CaseStepSelector(case: String, detail: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            Modifier
                .height(70.dp)
                .width(3.dp)
                .background(MainGreen)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = case,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                color = Color.Black
            )

            Text(
                text = detail,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                color = Color.Black
            )
        }
    }
}