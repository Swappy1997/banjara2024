package com.example.banjaraworld.presentation.marriageregistration.seventh

import NumberPickerWithSuffix
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.ui.theme.BanjaraWorldTheme
import com.example.banjaraworld.ui.theme.onPrimary

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MarriageHeightScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit,
    viewModel: MarriageHeightScreenViewmodel = hiltViewModel()
) {
    val state = viewModel.state

    val (cmValue, setCmValue) = remember { mutableStateOf(155) }
    val (ftValue, setFtValue) = remember { mutableStateOf(5) }
    val (inchValue, setInchValue) = remember { mutableStateOf(3) }
    val (selectedUnit, setSelectedUnit) = remember { mutableStateOf(state.selectedUnit) }

    val cmRange = 155..201
    val ftRange = 5..8
    val inchRange = 1..12
    val currentRange = if (selectedUnit == "cm") cmRange else inchRange

    BanjaraWorldTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(BwDimensions.PADDING_8)
                .systemBarsPadding()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_8)
            ) {

                item {
                    LinearDeterminateIndicator(progressValue = 0.4f)
                }
                item {
                    CommonText(
                        text = "What is your height",
                        fontSize = BwDimensions.TITTLE_FONT_SIZE,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                    )

                }
            }
            Column(
                modifier
                    .fillMaxWidth()
                    .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    NumberPickerWithSuffix(
                        range = if (selectedUnit != "cm") ftRange else currentRange,
                        selectedValue = if (selectedUnit != "cm") ftValue else cmValue,
                        onValueChange = {
                            if (selectedUnit != "cm") setFtValue(it) else setCmValue(it)
                        }
                    )
                    CommonText(
                        text = if (selectedUnit != "cm") "ft" else "cm",
                        fontSize = BwDimensions.TITTLE_FONT_SIZE,
                        color = onPrimary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                    )
                    if (selectedUnit != "cm") {
                        NumberPickerWithSuffix(
                            range = inchRange,
                            selectedValue = inchValue,
                            onValueChange = setInchValue
                        )
                        CommonText(
                            text = selectedUnit,
                            fontSize = BwDimensions.TITTLE_FONT_SIZE,
                            color = onPrimary,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                        )
                    }
                }
                Spacer(modifier = Modifier.height(BwDimensions.SPACING_24))
                UnitSwitch(
                    selectedUnit = selectedUnit,
                    onUnitChange = setSelectedUnit,
                )
            }

            CommonButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = stringResource(id = R.string.continues),
                onClick = onContinueClick
            )
        }
    }
}

@Composable
fun UnitSwitch(
    modifier: Modifier = Modifier,
    selectedUnit: String,
    onUnitChange: (String) -> Unit
) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val units = listOf("cm", "inch")
        units.forEach { unit ->
            SuggestionChip(
                onClick = { onUnitChange(unit) },
                label = {
                    Text(
                        text = unit,
                        fontSize = 16.sp,
                        color = if (selectedUnit == unit) Color.Black else Color.Gray,
                        fontWeight = if (selectedUnit == unit) FontWeight.Bold else FontWeight.Medium,
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (selectedUnit == unit) onPrimary else Color.White
                ),
                border = null,
                elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
            )
        }
    }
}
