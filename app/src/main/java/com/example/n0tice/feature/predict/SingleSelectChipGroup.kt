package com.example.n0tice.feature.predict

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.preFontFamily

@Composable
fun <T> SingleSelectChipGroup(
    options: List<T>,
    selectedOption: T?,
    onOptionSelected: (T) -> Unit,
    rows: Int = 2,
    labelFor: (T) -> String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.chunked(rows).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                rowItems.forEach { option ->
                    FilterChip(
                        selected = (option == selectedOption),
                        onClick = { onOptionSelected(option) },
                        label = {
                            Text(
                                modifier = Modifier.padding(vertical = 12.dp),
                                text = labelFor(option),
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MainGreen,
                            selectedLabelColor = Color.White,
                            containerColor = Color.White,
                            labelColor = Color.Black
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            selected = true,
                            enabled = true,
                            selectedBorderWidth = 1.8.dp,
                            borderWidth = 1.8.dp,
                            selectedBorderColor = MainGreen,
                            disabledBorderColor = MainGreen
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
