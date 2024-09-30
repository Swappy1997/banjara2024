package com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.common.utils.Utils.formatText
import com.example.banjaraworld.presentation.ErrorText
import com.example.banjaraworld.presentation.commonwidgets.AdvancedTimePickerExample
import com.example.banjaraworld.presentation.commonwidgets.ChipGroup
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.DatePickerWithDialog
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.presentation.commonwidgets.SectionTitle
import com.example.banjaraworld.ui.theme.PoppinsFont
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
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
                LinearDeterminateIndicator(progressValue = 0.1f)
            }
            item {
                Spacer(modifier = Modifier.height(BwDimensions.SPACING_8))
            }
            item {

                Text(
                    text = formatText(
                        prefix = "Basic\n ",
                        discountColor = onPrimary,
                        formattedText = "Details",
                    ), fontSize = BwDimensions.FONT_23, fontFamily = PoppinsFont
                )

            }

            item { SectionTitle("What is your gender?") }
            item {
                ChipGroup(
                    options = listOf("Male", "Female", "Other"),
                    selectedOption = state.isGenderSelected,
                    onOptionSelected = {
                        marriageFirstScreenViewmodel.onEvent(
                            MarriageFirstScreenEvent.GenderChanged(it)
                        )
                    }
                )
                ErrorText(state.isGenderSelectedError)
            }


            item {
                SectionTitle(text = "What is your marital status ?")
            }

            item {
                ChipGroup(
                    options = listOf("Single", "Married", "Divorced", "Widowed"),
                    selectedOption = state.isMarriageStatusSelected,
                    onOptionSelected = {
                        marriageFirstScreenViewmodel.onEvent(
                            MarriageFirstScreenEvent.MarriageStatusChanged(
                                it
                            )
                        )
                    }
                )
                ErrorText(state.isMarriageStatusSelectedError)
            }


            item {
                SectionTitle(text = "Profile created for ?")
            }
            item {
                ChipGroup(
                    options = marriageFirstScreenViewmodel.listProfileCreatedFor,
                    selectedOption = state.isProfileCreatedForSelected,
                    onOptionSelected = {
                        marriageFirstScreenViewmodel.onEvent(
                            MarriageFirstScreenEvent.ProfileCreatedForChanged(it)
                        )
                    }
                )
                ErrorText(state.isProfileCreatedForSelectedError)
            }


            item {
                SectionTitle(text = "What is your diet preference ?")
            }
            item {
                ChipGroup(
                    options = marriageFirstScreenViewmodel.listDietPreferences,
                    selectedOption = state.isDietPreferenceSelected,
                    onOptionSelected = {
                        marriageFirstScreenViewmodel.onEvent(
                            MarriageFirstScreenEvent.MarriageDietPreference(it)
                        )
                    }
                )
                ErrorText(state.isDietPreferenceSelectedError)
            }
            item {
                SectionTitle(text = "What is your birth date?")
                SuggestionChip(
                    onClick = {
                        marriageFirstScreenViewmodel.onEvent(MarriageFirstScreenEvent.ToggleDateChipClick)
                    },
                    label = {
                        CommonText(
                            text = state.birthDate,
                            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                        )
                    },
                    icon = {
                        Icon(
                            Icons.Filled.EditCalendar,
                            contentDescription = "Select Date",
                            tint = onSecondary
                        )
                    }
                )
                ErrorText(errorMessage = state.isBirthDateSelectedError)
            }
            item {
                SectionTitle(text = "What is your birth time?")

                SuggestionChip(
                    onClick = {
                        marriageFirstScreenViewmodel.onEvent(MarriageFirstScreenEvent.ToggleTimeChipClick)
                    },
                    label = {
                        CommonText(
                            text = state.birthTime,
                            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                        )
                    },
                    icon = {
                        Icon(
                            Icons.Filled.AccessTime,
                            contentDescription = "Select Time",
                            tint = onSecondary
                        )
                    }
                )
            }
            item {
                if (state.dateChipClick) {
                    DatePickerWithDialog(
                        onDateSelected = { selectedDate ->
                            marriageFirstScreenViewmodel.onEvent(
                                MarriageFirstScreenEvent.BirthDateChanged(
                                    selectedDate
                                )
                            )
                        },
                        onDismiss = {
                            marriageFirstScreenViewmodel.onEvent(MarriageFirstScreenEvent.ToggleDateChipClick)
                        }
                    )
                }
            }
            item {
                if (state.timeChipClick) {
                    AdvancedTimePickerExample(
                        onConfirm = { timePickerState ->
                            val selectedTime = "${timePickerState.hour}:${timePickerState.minute}"
                            marriageFirstScreenViewmodel.onEvent(
                                MarriageFirstScreenEvent.BirthTimeChanged(
                                    selectedTime
                                )
                            )
                        },
                        onDismiss = {
                            marriageFirstScreenViewmodel.onEvent(MarriageFirstScreenEvent.ToggleTimeChipClick)
                        }
                    )
                }

            }
            item {
                Spacer(modifier = Modifier.padding(bottom = BwDimensions.SPACING_50))
            }
        }
        Spacer(modifier = Modifier.padding(top = BwDimensions.SPACING_50))

        CommonButton(
            text = stringResource(id = R.string.continues),
            onClick = { marriageFirstScreenViewmodel.onEvent(MarriageFirstScreenEvent.Continue) },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

    }
}
