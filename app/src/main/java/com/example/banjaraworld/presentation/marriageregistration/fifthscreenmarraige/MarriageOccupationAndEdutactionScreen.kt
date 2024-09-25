package com.example.banjaraworld.presentation.marriageregistration.fifthscreenmarraige

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MarriageOccupationAndEducationScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit
) {
    val motherOccupationList = listOf(
        "House wife", "Business", "Retired", "Gov employee", "working in private sector"
    )

    val fatherOccupationList = listOf(
        "Business", "not working", "Retired", "Gov employee", "working in private sector"
    )
    val highestEducationList =
        listOf("Graduate", "Post Graduate", "Diploma", "Under Graduate", "Ssc", "HSC", "Other")

    val annualIncomeList = listOf(
        "0-1L",
        "1-5L",
        "5-10L",
        "10-20L",
        "20-30L",
        "30-40L",
        "40-50L",
        "50-100L",
        "above 1Cr"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(BwDimensions.PADDING_8)
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_8)
        ) {
            item {
                LinearDeterminateIndicator(0.9f)
            }
            item {
                CommonText(
                    text = "What is your  highest education?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                CommonOutlineTextField(
                    modifier = Modifier,
                    dummyText = "What is your highest education",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                )
            }
            item {
                CommonText(
                    text = "What is your current occupation?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                CommonOutlineTextField(
                    modifier = Modifier,
                    dummyText = "What is your current occupation",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                )
            }
            item {
                CommonText(
                    text = "what is your annual income in INR?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    annualIncomeList.forEach {
                        SuggestionChip(
                            onClick = {

                            },
                            label = {
                                CommonText(
                                    text = it,
                                    fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(containerColor = Color.White),
                            border = null,
                            elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
                        )
                    }
                }
            }
            item {
                CommonText(
                    text = "what is your father occupation?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    fatherOccupationList.forEach {
                        SuggestionChip(
                            onClick = {

                            },
                            label = {
                                CommonText(
                                    text = it,
                                    fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(containerColor = Color.White),
                            border = null,
                            elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
                        )
                    }
                }
            }
            item {
                CommonText(
                    text = "what is your mother Occupation ?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    motherOccupationList.forEach {
                        SuggestionChip(
                            onClick = {
                            },
                            label = {
                                CommonText(
                                    text = it,
                                    fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(containerColor = Color.White),
                            border = null,
                            elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
                        )
                    }
                }
            }
        }
        CommonButton(text = stringResource(id = R.string.continues), onClick = {
            onContinueClick.invoke()
        }, modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter))

    }
}