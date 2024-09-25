package com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator

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

                LinearDeterminateIndicator(0.2f)
            }
            item {
                CommonText(
                    stringResource(R.string.whats_your_name),
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
                CommonText(
                    stringResource(R.string.lets_get_to_know_each_other),
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
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
                Spacer(modifier = Modifier.padding(bottom = BwDimensions.SPACING_30))
            }
        }

        CommonButton(
            stringResource(R.string.continues),
            onClick = { marriageSecondScreenViewModel.onEvent(MarriageSecondScreenEvent.Continue) },
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}