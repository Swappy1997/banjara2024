package com.example.banjaraworld.presentation.marriageregistration.userbirthandtime

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.*
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBirthDateAndTime(modifier: Modifier = Modifier, onContiueClick: () -> Unit) {

    var birthTime by remember { mutableStateOf("select time") }
    var birthDate by remember { mutableStateOf("select date") }
    var timeChipClick by remember { mutableStateOf(false) }
    var dateChipClick by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(BwDimensions.PADDING_8),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_5)
    ) {
        LinearDeterminateIndicator(0.3f)
        CommonText(
            text = "What is your birth date",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = BwDimensions.FONT_16,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
        SuggestionChip(
            onClick = {dateChipClick =true},
            label = {
                CommonText(
                    text = birthDate,
                    color = onPrimary,
                    textAlign = TextAlign.Center,
                    fontSize = BwDimensions.FONT_12,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )
            }, icon = {
                Icon(Icons.Filled.EditCalendar, contentDescription = "date", tint = onSecondary)
            }
        )

        CommonText(
            text = "What is your birth time",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = BwDimensions.FONT_16,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )

        SuggestionChip(
            onClick = { timeChipClick = true },
            label = {
                CommonText(
                    birthTime,
                    color = onPrimary,
                    textAlign = TextAlign.Center,
                    fontSize = BwDimensions.FONT_12,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )
            },
            icon = {
                Icon(Icons.Filled.AccessTime, contentDescription = "time", tint = onSecondary)
            }
        )
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
        Spacer(modifier = Modifier.weight(1f))
        CommonButton(text = stringResource(R.string.continues), onClick = {
            onContiueClick.invoke()
        })
    }

}
