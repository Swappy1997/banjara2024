package com.example.banjaraworld.presentation.marriageregistration.thirdscreenmarriage

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.Text
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.common.utils.Utils.formatText
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.presentation.marriageregistration.MarriageRegistrationViewmodel
import com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage.MarriageSecondScreenEvent
import com.example.banjaraworld.ui.theme.BanjaraWorldTheme
import com.example.banjaraworld.ui.theme.PoppinsFont
import com.example.banjaraworld.ui.theme.onPrimary

@Composable
fun MarriageStateAndCityScreen(
    modifier: Modifier = Modifier,
    viewmodel: MarriageRegistrationViewmodel = hiltViewModel(),
    onContinueClick: () -> Unit
) {

    val context = LocalContext.current
    val state = viewmodel.state.collectAsStateWithLifecycle().value
    val city = viewmodel.city.collectAsStateWithLifecycle().value
    val permissionIsGranted = viewmodel.isPermissionGranted.collectAsStateWithLifecycle().value

    BanjaraWorldTheme(dynamicColor = false) {

        val locationPermissionResultluncher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { grant ->
                viewmodel.onPermissionResult(
                    permission = Manifest.permission.ACCESS_FINE_LOCATION, isGranted = grant
                )
            }
        LaunchedEffect(key1 = context) {
            locationPermissionResultluncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
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

                    LinearDeterminateIndicator(progressValue = 0.5f)
                }
                item {
                    Text(
                        text = formatText(
                            prefix = "Contact\n ",
                            discountColor = onPrimary,
                            formattedText = "Details ?",
                        ), fontSize = BwDimensions.FONT_23, fontFamily = PoppinsFont
                    )
                }
                item {
                    CommonText(
                        text = "Please add mobile number ",
                        fontSize = BwDimensions.TITTLE_FONT_SIZE,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )

                    CommonOutlineTextField(
                        dummyText = stringResource(R.string.enter_mobile),
                        keyboardType = KeyboardType.Phone,
                        modifier = Modifier,
                        onValueChange = {
                        },
                        value = "",
                        imeAction = ImeAction.Done
                    )
//                    AnimatedVisibility(visible = state.firstNameError != null) {
//                        state.firstNameError?.let {
//
//                            CommonText(
//                                text = it,
//                                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
//                                fontWeight = FontWeight.Light,
//                                textAlign = TextAlign.Start,
//                                color = MaterialTheme.colorScheme.error,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(start = BwDimensions.PADDING_10)
//                            )
//
//                        }
//                    }
                }
                item {
                    CommonText(
                        text = "Please add your email id",
                        fontSize = BwDimensions.TITTLE_FONT_SIZE,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
                    CommonOutlineTextField(
                        dummyText = stringResource(R.string.enter_email_id),
                        keyboardType = KeyboardType.Email,
                        modifier = Modifier,
                        onValueChange = {
//                            marriageSecondScreenViewModel.onEvent(
//                                MarriageSecondScreenEvent.MiddleNameChanged(it)
//                            )
                        },
                        value = "",
                        imeAction = ImeAction.Done
                    )
//                    AnimatedVisibility(visible = state.middleNameError != null) {
//                        state.middleNameError?.let {
//
//                            CommonText(
//                                text = it,
//                                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
//                                fontWeight = FontWeight.Light,
//                                textAlign = TextAlign.Start,
//                                color = MaterialTheme.colorScheme.error,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(start = BwDimensions.PADDING_10)
//                            )
//
//                        }
//                    }
                }
                item {
                    CommonText(
                        text = "What is your current State?",
                        fontSize = BwDimensions.TITTLE_FONT_SIZE,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
                    CommonOutlineTextField(
                        modifier = Modifier,
                        value = state,
                        onValueChange = {},
                        dummyText = "What is your current state",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                        readOnly = permissionIsGranted,
                    )

                }
                item {
                    CommonText(
                        text = "What is your current City?",
                        fontSize = BwDimensions.TITTLE_FONT_SIZE,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
                    CommonOutlineTextField(
                        modifier = Modifier,
                        value = city,
                        onValueChange = {},
                        dummyText = "What is your home city",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                        readOnly = permissionIsGranted
                    )

                }
            }
            CommonButton(text = stringResource(id = R.string.continues), onClick = {
                onContinueClick.invoke()
            }, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter))
        }
    }
}