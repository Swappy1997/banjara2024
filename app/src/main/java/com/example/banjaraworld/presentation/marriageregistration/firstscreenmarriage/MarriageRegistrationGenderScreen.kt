package com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Boy
import androidx.compose.material.icons.filled.Girl
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.presentation.commonwidgets.RoundedButton
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MarriageRegistrationGenderScreen(
    onContinue: () -> Unit = {},
    marriageFirstScreenViewmodel: MarriageFirstScreenViewmodel = hiltViewModel()
) {
    val state = marriageFirstScreenViewmodel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        marriageFirstScreenViewmodel.validationEvents.collect { event ->
            when (event) {
                is MarriageFirstScreenViewmodel.ValidationEvent.Success -> {
                    onContinue()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BwDimensions.PADDING_8),
        verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_8)
    ) {
        LinearDeterminateIndicator(progressValue = 0.1f)
        Spacer(modifier = Modifier.height(BwDimensions.SPACING_12))
        CommonText(
            text = "What is your gender?",
            fontSize = BwDimensions.FONT_16,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            AssistChip(
                onClick = {
                    marriageFirstScreenViewmodel.onEvent(
                        MarriageFirstScreenEvent.GenderChanged("male")
                    )
                },
                label = {
                    CommonText(
                        text = "Male",
                        fontSize = BwDimensions.FONT_12,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Boy,
                        contentDescription = "male",
                        tint = onSecondary
                    )
                },
                colors = if (state.isGenderSelected == "male") AssistChipDefaults.assistChipColors(
                    containerColor = onPrimary
                ) else AssistChipDefaults.assistChipColors(containerColor = Color.White),
                border = null,
                elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
            )
            AssistChip(
                onClick = {
                    marriageFirstScreenViewmodel.onEvent(
                        MarriageFirstScreenEvent.GenderChanged("female")
                    )
                },
                label = {
                    CommonText(
                        text = "Female",
                        fontSize = BwDimensions.FONT_12,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Girl,
                        contentDescription = "female",
                        tint = onSecondary
                    )
                },
                colors = if (state.isGenderSelected == "female") AssistChipDefaults.assistChipColors(
                    containerColor = onPrimary
                ) else AssistChipDefaults.assistChipColors(containerColor = Color.White),
                border = null,
                elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
            )
        }
        AnimatedVisibility(
            visible = state.isGenderSelectedError != null,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            state.isGenderSelectedError?.let {

                CommonText(
                    text = it,
                    fontSize = BwDimensions.FONT_12,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = BwDimensions.PADDING_10)
                )
            }
        }
        Spacer(modifier = Modifier.height(BwDimensions.SPACING_8))
        CommonText(
            text = "What is your marital status ?",
            fontSize = BwDimensions.FONT_16,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
        )

        val listOfMaritalStatus = listOf("Single", "Married", "Divorced", "Widowed")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            listOfMaritalStatus.forEach {
                SuggestionChip(
                    onClick = {
                        marriageFirstScreenViewmodel.onEvent(
                            MarriageFirstScreenEvent.MarriageStatusChanged(
                                it
                            )
                        )
                    },
                    label = {
                        CommonText(
                            text = it,
                            fontSize = BwDimensions.FONT_12,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                        )
                    },
                    colors = if (state.isMarriageStatusSelected == it) AssistChipDefaults.assistChipColors(
                        containerColor = onPrimary
                    ) else AssistChipDefaults.assistChipColors(containerColor = Color.White),
                    border = null,
                    elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
                )
            }
        }
        AnimatedVisibility(
            visible = state.isMarriageStatusSelectedError != null,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            state.isMarriageStatusSelectedError?.let {
                CommonText(
                    text = it,
                    fontSize = BwDimensions.FONT_12,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = BwDimensions.PADDING_10)
                )
            }
        }

        val listProfileCreatedFor =
            listOf("Son", "Daughter", "sister", "Brother", "Friend", "Myself")
        CommonText(
            text = "Profile created for ?",
            fontSize = BwDimensions.FONT_16,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            listProfileCreatedFor.forEach {
                SuggestionChip(
                    onClick = {
                        marriageFirstScreenViewmodel.onEvent(
                            MarriageFirstScreenEvent.ProfileCreatedForChanged(
                                it
                            )
                        )
                    },
                    label = {
                        CommonText(
                            text = it,
                            fontSize = BwDimensions.FONT_12,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                        )
                    },
                    colors = if (state.isProfileCreatedForSelected == it) AssistChipDefaults.assistChipColors(
                        containerColor = onPrimary
                    ) else AssistChipDefaults.assistChipColors(containerColor = Color.White),
                    border = null,
                    elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
                )
            }
        }
        AnimatedVisibility(
            visible = state.isProfileCreatedForSelectedError != null,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            state.isProfileCreatedForSelectedError?.let {
                CommonText(
                    text = it,
                    fontSize = BwDimensions.FONT_12,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = BwDimensions.PADDING_10)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        RoundedButton(
            text = stringResource(id = R.string.continues),
            onClick = { marriageFirstScreenViewmodel.onEvent(MarriageFirstScreenEvent.Continue) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
