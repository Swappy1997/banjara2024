package com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Boy
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Girl
import androidx.compose.material3.AssistChip
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.AdvancedTimePickerExample
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.DatePickerWithDialog
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
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
    val listProfileCreatedFor =
        listOf("Son", "Daughter", "sister", "Brother", "Friend", "Myself")
    val listDietPreferences =
        listOf("Vegetarian", "Non-Vegetarian", "Vegan", "Occasional Non-Vegetarian")

    var birthTime by remember { mutableStateOf("select time") }
    var birthDate by remember { mutableStateOf("select date") }
    var timeChipClick by remember { mutableStateOf(false) }
    var dateChipClick by remember { mutableStateOf(false) }

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
                CommonText(
                    text = "Basic Details",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth(),
                )
            }
            item {
                Spacer(modifier = Modifier.height(BwDimensions.SPACING_12))
            }
            item {
                CommonText(
                    text = "What is your gender?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
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
                                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
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
                                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
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
                            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = BwDimensions.PADDING_10)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(BwDimensions.SPACING_8))
            }
            item {
                CommonText(
                    text = "What is your marital status ?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
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
                                    fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
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
                            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = BwDimensions.PADDING_10)
                        )
                    }
                }

            }
            item {

                CommonText(
                    text = "Profile created for ?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
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
                                    fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
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
                            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = BwDimensions.PADDING_10)
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(BwDimensions.SPACING_8))
            }
            item {

                CommonText(
                    text = "What is your diet preference ?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    listDietPreferences.forEach {
                        SuggestionChip(
                            onClick = {
                                marriageFirstScreenViewmodel.onEvent(
                                    MarriageFirstScreenEvent.MarriageDietPreference(
                                        it
                                    )
                                )
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
                            colors = if (state.isDietPreferenceSelected == it) AssistChipDefaults.assistChipColors(
                                containerColor = onPrimary
                            ) else AssistChipDefaults.assistChipColors(containerColor = Color.White),
                            border = null,
                            elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
                        )
                    }
                }
                AnimatedVisibility(
                    visible = state.isDietPreferenceSelectedError != null,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    state.isDietPreferenceSelectedError?.let {
                        CommonText(
                            text = it,
                            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = BwDimensions.PADDING_10)
                        )
                    }
                }
            }

            item {
                CommonText(
                    text = "What is your birth date",
                    color = Color.Black,
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )
                SuggestionChip(
                    onClick = {
                        dateChipClick = true
                        marriageFirstScreenViewmodel.onEvent(
                            MarriageFirstScreenEvent.BirthDateChanged(
                                birthDate
                            )
                        )

                    },
                    label = {
                        CommonText(
                            text = birthDate,
                            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                        )
                    }, icon = {
                        androidx.compose.material.Icon(
                            Icons.Filled.EditCalendar,
                            contentDescription = "date",
                            tint = onSecondary
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(containerColor = Color.White),
                    border = null,
                    elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)

                )
                AnimatedVisibility(
                    visible = state.isBirthDateSelectedError != null,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    state.isBirthDateSelectedError?.let {
                        CommonText(
                            text = it,
                            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = BwDimensions.PADDING_10)
                        )
                    }
                }

            }
            item {
                CommonText(
                    text = "What is your birth time",
                    color = Color.Black,
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )

                SuggestionChip(
                    onClick = { timeChipClick = true },
                    label = {
                        CommonText(
                            birthTime,
                            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                        )
                    },
                    icon = {
                        androidx.compose.material.Icon(
                            Icons.Filled.AccessTime,
                            contentDescription = "time",
                            tint = onSecondary
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(containerColor = Color.White),
                    border = null,
                    elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)

                )
            }
            item {
                if (timeChipClick) {
                    AdvancedTimePickerExample(
                        onConfirm = { timePickerState ->
                            val selectedTime = "${timePickerState.hour}:${timePickerState.minute}"
                            birthTime = selectedTime
                            Log.d("TimePicker", "Selected time: $selectedTime")
                            timeChipClick = false
                        },
                        onDismiss = { timeChipClick = false }
                    )
                }
            }
            item {
                if (dateChipClick) {
                    DatePickerWithDialog(
                        onDateSelected = { selectedDate ->
                            birthDate = selectedDate
                            Log.d("DatePicker", "Selected date: $selectedDate")
                            dateChipClick = false
                        },
                        onDismiss = { dateChipClick = false },

                        )
                }
            }
            item {
                Spacer(modifier = Modifier.padding(bottom = BwDimensions.SPACING_30))
            }

        }
        CommonButton(
            text = stringResource(id = R.string.continues),
            onClick = { marriageFirstScreenViewmodel.onEvent(MarriageFirstScreenEvent.Continue) },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

    }
}
