package com.example.banjaraworld.presentation.marriageregistration.sixthmarriagescreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextAlign
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.AdvancedTimePickerExample
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.DatePickerWithDialog
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBirthDateAndTime(modifier: Modifier = Modifier, onContiueClick: () -> Unit) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(BwDimensions.PADDING_8)
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                LinearDeterminateIndicator(0.3f)
            }
        }

        CommonButton(
            text = stringResource(R.string.continues), onClick = {
                onContiueClick.invoke()
            }, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

    }
}
