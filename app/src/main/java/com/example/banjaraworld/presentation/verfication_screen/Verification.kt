package com.example.banjaraworld.presentation.verfication_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banjaraworld.R
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.OtpInput
import com.example.banjaraworld.ui.theme.onSecondary

@Composable
fun VerficationScreen(

    verificationOtp: VerificationOtp = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {

    var state = verificationOtp.state
    val paddingValues = WindowInsets.navigationBars.asPaddingValues()
    val context = LocalContext.current

    LaunchedEffect(context) {
        verificationOtp.validationEvents.collect { event ->
            when (event) {
                is VerificationOtp.ValidationEvent -> {
                    navigateToHomeScreen.invoke()
                    Toast.makeText(context, "Otp Verified", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = WindowInsets.statusBars.asPaddingValues()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.weight(1f))
            CommonText(
                text = "Verification code",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = onSecondary
            )

            Spacer(Modifier.height(8.dp))
            CommonText(
                text = "${stringResource(R.string.otp_recived_msg)}\n +917720840636",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = onSecondary
            )
            Spacer(Modifier.height(8.dp))
            OtpInput(otpValue = state.otp, onValueChange = {
                verificationOtp.OnEvent(VerificationOtpEvent.OptChanged(it))
            })
            Spacer(Modifier.padding(8.dp))
            if (state.otpError != null) {
                CommonText(
                    text = state.otpError!!,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }
            Spacer(Modifier.weight(1f))
            CommonButton(stringResource(R.string.verify), onClick = {
                verificationOtp.OnEvent(VerificationOtpEvent.Verify)
            }, Modifier.padding(paddingValues))

        }
    }
}