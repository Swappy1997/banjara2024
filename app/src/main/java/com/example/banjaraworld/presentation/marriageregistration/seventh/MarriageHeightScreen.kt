package com.example.banjaraworld.presentation.marriageregistration.seventh

import NumberPickerWithSuffix
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    oncontinueClick: () -> Unit,
    marriageHeightScreenViewmodel: MarriageHeightScreenViewmodel
) {

    var selectedUnit by remember { mutableStateOf("cm") }

    var cmValue by remember { mutableStateOf(155) } // Default to 155 cm
    var inchValue by remember { mutableStateOf(3) }
    var ftValue by remember { mutableStateOf(5) }

    // Define ranges based on selected unit
    val cmlist = 155..200
    val ftlist = 5..7  // Assuming 5' to 6' as range for simplicity
    val inchlist = 1..11  // Assuming 5.5 inches to 6.5 inches as range for simplicity

    val currentRange = if (selectedUnit == "cm") cmlist else inchlist

    BanjaraWorldTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(BwDimensions.PADDING_8),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LinearDeterminateIndicator(progressValue = 0.4f)
            CommonText(
                text = "What is your height",
                fontSize = BwDimensions.FONT_16,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                NumberPickerWithSuffix(range = if (selectedUnit != "cm") ftlist else currentRange,
                    selectedValue = if (selectedUnit != "cm") ftValue else cmValue,
                    onValueChange = {
                        if (selectedUnit != "cm") {
                            ftValue = it
                        } else {
                            cmValue = it
                        }
                    })
                CommonText(
                    text = if (selectedUnit != "cm") "ft" else "cm",
                    fontSize = BwDimensions.FONT_16,
                    color = onPrimary,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )
                if (selectedUnit != "cm") {
                    NumberPickerWithSuffix(
                        range = currentRange,
                        selectedValue = inchValue,
                        onValueChange = {
                            inchValue = it
                        })
                    CommonText(
                        text = selectedUnit,
                        fontSize = BwDimensions.FONT_16,
                        color = onPrimary,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
                }
            }
            Spacer(modifier = Modifier.height(BwDimensions.SPACING_24))
            SwitchCmToInch(selectedUnit = selectedUnit, onUnitChange = { unit ->
                selectedUnit = unit
                // Convert the selectedValue if needed
                // You might want to scale or adjust the value based on the unit change
            })
            Spacer(modifier = Modifier.weight(1f))
            CommonButton(
                modifier = Modifier.navigationBarsPadding(),
                text = stringResource(id = R.string.continues),
                onClick = { oncontinueClick.invoke() })
        }

    }
}

@Composable
fun SwitchCmToInch(
    modifier: Modifier = Modifier, selectedUnit: String, onUnitChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        SuggestionChip(onClick = { /*TODO*/ }, label = {
            Text(text = "cm",
                fontSize = 16.sp,
                color = if (selectedUnit == "cm") Color.Black else Color.Gray,
                fontWeight = if (selectedUnit == "cm") FontWeight.Bold else FontWeight.Medium,
                modifier = Modifier.clickable {
                    onUnitChange("cm")
                })
        },
            colors = if (selectedUnit == "cm") AssistChipDefaults.assistChipColors(
                containerColor = onPrimary
            ) else AssistChipDefaults.assistChipColors(containerColor = Color.White),
            border = null,
            elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
        )

        SuggestionChip(onClick = { /*TODO*/ }, label = {
            Text(text = "inch",
                fontSize = 16.sp,
                color = if (selectedUnit == "inch") Color.Black else Color.Gray,
                fontWeight = if (selectedUnit == "inch") FontWeight.Bold else FontWeight.Medium,
                modifier = Modifier.clickable {
                    onUnitChange("inch")
                })
        },
            colors = if (selectedUnit == "inch") AssistChipDefaults.assistChipColors(
                containerColor = onPrimary
            ) else AssistChipDefaults.assistChipColors(containerColor = Color.White),
            border = null,
            elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
        )

    }
}
