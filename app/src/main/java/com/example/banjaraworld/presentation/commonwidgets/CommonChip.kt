package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.ui.theme.onPrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroup(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_8),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        options.forEach { option ->
            FilterChip(
                selected = selectedOption == option,
                onClick = { onOptionSelected(option) },
                label = {
                    CommonText(
                        text = option,
                        fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier,
                    )
                },
                border = null,
                elevation = FilterChipDefaults.filterChipElevation(1.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = onPrimary,
                    containerColor = Color.White
                )
            )
        }
    }
}