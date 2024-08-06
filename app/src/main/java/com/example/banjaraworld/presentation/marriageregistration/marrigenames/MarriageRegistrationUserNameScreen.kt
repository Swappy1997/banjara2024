package com.example.banjaraworld.presentation.marriageregistration.marrigenames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.presentation.commonwidgets.RoundedButton
import com.example.banjaraworld.ui.theme.background

@Composable
fun MarriageRegistrationUserNameScreen(modifier: Modifier = Modifier, onContinueClick: () -> Unit) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
            .statusBarsPadding()
            .padding(BwDimensions.PADDING_8)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_5),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LinearDeterminateIndicator(0.1f)
        CommonText(
            stringResource(R.string.whats_your_name),
            fontSize = BwDimensions.FONT_16,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        CommonText(
            stringResource(R.string.lets_get_to_know_each_other),
            fontSize = BwDimensions.FONT_16,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )

        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {},
            imeAction = ImeAction.Done
        )
        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_middle_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {},
            imeAction = ImeAction.Done
        )
        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_last_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {},
            imeAction = ImeAction.Done
        )
        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_mother_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {},
            imeAction = ImeAction.Done
        )
        Spacer(modifier = Modifier.weight(1f))
        RoundedButton(
            stringResource(R.string.continues),
            onClick = { onContinueClick.invoke() },
            modifier = modifier.fillMaxWidth()
        )
    }
}