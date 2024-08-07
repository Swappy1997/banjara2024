package com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.presentation.commonwidgets.RoundedButton

@Composable
fun MarriageRegistrationUserNameScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit,
    marriageSecondScreenViewModel: MarriageSecondScreenViewModel = hiltViewModel()
) {

    val state = marriageSecondScreenViewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        marriageSecondScreenViewModel.validationEvents.collect { event ->
            when (event) {
                is MarriageSecondScreenViewModel.ValidationEvent.Success -> {
                    onContinueClick()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
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
            onValueChange = {
                marriageSecondScreenViewModel.onEvent(
                    MarriageSecondScreenEvent.FirstNameChanged(it)
                )
            },
            value = state.firstName,
            imeAction = ImeAction.Done
        )
        AnimatedVisibility(visible = state.firstNameError != null) {
            state.firstNameError?.let {

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
        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_middle_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {
                marriageSecondScreenViewModel.onEvent(
                    MarriageSecondScreenEvent.MiddleNameChanged(it)
                )
            },
            value = state.middleName,
            imeAction = ImeAction.Done
        )
        AnimatedVisibility(visible = state.middleNameError != null) {
            state.middleNameError?.let {

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
        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_last_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {
                marriageSecondScreenViewModel.onEvent(
                    MarriageSecondScreenEvent.LastNameChanged(it)
                )
            },
            value = state.lastName,
            imeAction = ImeAction.Done
        )

        AnimatedVisibility(visible = state.lastNameError != null) {
            state.lastNameError?.let {

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

        CommonOutlineTextField(
            dummyText = stringResource(R.string.enter_your_mother_name),
            keyboardType = KeyboardType.Text,
            modifier = Modifier,
            onValueChange = {
                marriageSecondScreenViewModel.onEvent(
                    MarriageSecondScreenEvent.MotherNameChanged(it)
                )
            },
            value = state.motherName,
            imeAction = ImeAction.Done
        )
        AnimatedVisibility(visible = state.motherNameError != null) {
            state.motherNameError?.let {

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
            stringResource(R.string.continues),
            onClick = { marriageSecondScreenViewModel.onEvent(MarriageSecondScreenEvent.Continue) },
            modifier = modifier.fillMaxWidth()
        )
    }
}