package com.example.banjaraworld.presentation.marriageregistration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.navigation.Screen
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator

@Composable
fun MarriageRegistrationScreen(modifier: Modifier = Modifier) {

    Column {
        LinearDeterminateIndicator(0.1f)
        CommonText(
            stringResource(R.string.whats_your_name),
            fontSize = BwDimensions.FONT_16,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
        )
        CommonText(
            stringResource(R.string.lets_get_to_know_each_other),
            fontSize = BwDimensions.FONT_16,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
        )

        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {},
            imeAction = ImeAction.Done
        )
        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {},
            imeAction = ImeAction.Done
        )
        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {},
            imeAction = ImeAction.Done
        )
        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {},
            imeAction = ImeAction.Done
        )
        Modifier.weight(1f)
        CommonButton(stringResource(R.string.continues), onClick = {})
    }
}