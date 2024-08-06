package com.example.banjaraworld.presentation.marriageregistration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.RoundedButton

@Composable
fun MarriageOccupationAndEducationScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LinearProgressIndicator(progress = 0.6f)
        CommonText(
            text = "What is your  highest education?",
            fontSize = BwDimensions.FONT_16,
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
        CommonText(
            text = "What is your current occupation?",
            fontSize = BwDimensions.FONT_16,
            color = Color.Black,
            fontWeight = FontWeight.Bold, modifier = Modifier
        )
        CommonOutlineTextField(
            modifier = Modifier,
            dummyText = "What is your current occupation",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        )

        CommonText(
            text = "what is your annual income",
            fontSize = BwDimensions.FONT_16,
            color = Color.Black,
            fontWeight = FontWeight.Bold, modifier = Modifier
        )
        CommonOutlineTextField(
            modifier = Modifier,
            dummyText = "what is your annual income",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        )
        Spacer(modifier = Modifier.weight(1f))
        RoundedButton(text = stringResource(id = R.string.continues), onClick = {})

    }
}