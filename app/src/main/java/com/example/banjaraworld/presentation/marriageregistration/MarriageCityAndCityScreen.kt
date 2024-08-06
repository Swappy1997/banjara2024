package com.example.banjaraworld.presentation.marriageregistration

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.presentation.commonwidgets.RoundedButton
import com.example.banjaraworld.ui.theme.BanjaraWorldTheme

@Composable
fun MarriageStateAndCityScreen(
    modifier: Modifier = Modifier, viewmodel: MarriageRegistrationViewmodel = hiltViewModel()
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(BwDimensions.PADDING_8)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_8)
        ) {
            LinearDeterminateIndicator(progressValue = 0.5f)
            CommonText(
                text = "What is your current State?",
                fontSize = BwDimensions.FONT_16,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
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
            CommonText(
                text = "What is your current City?",
                fontSize = BwDimensions.FONT_16,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
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
            Spacer(modifier = Modifier.weight(1f))
            RoundedButton(text = stringResource(id = R.string.continues), onClick = {})
        }
    }
}
