package com.example.banjaraworld.presentation.login_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.banjaraworld.R
import com.example.banjaraworld.common.rememberImeState
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.common.utils.Utils
import com.example.banjaraworld.navigation.AuthScreen
import com.example.banjaraworld.presentation.SetBarStatus
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.ConnectivityStatusBox
import com.example.banjaraworld.presentation.commonwidgets.HyperlinkText
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary
import com.example.banjaraworld.ui.theme.surface
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val networkStatus = loginViewModel.networkStatus.collectAsState().value
    SetBarStatus(surface)
    val state = loginViewModel.state
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()
    val scope = rememberCoroutineScope()
    val paddingValues = WindowInsets.navigationBars.asPaddingValues()
    LaunchedEffect(context) {
        loginViewModel.validationEvents.collect { event ->
            when (event) {
                is LoginViewModel.ValidationEvent.Success -> {
                    navController.navigate(AuthScreen.Otp.route)
                    Utils.showToast("please enter otp",context)
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
                .padding(BwDimensions.PADDING_7)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ConnectivityStatus(networkStatus.isConnected)
            AsyncImage(
                model = R.drawable.logo,
                contentDescription = "logo",
                modifier = Modifier
                    .width(BwDimensions.ImageWidth)
            )
            Spacer(Modifier.height(BwDimensions.SPACING_24))
            CommonText(
                text = "Login",
                fontSize = BwDimensions.FONT_20,
                fontWeight = FontWeight.Bold,
                color = onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
            CommonText(
                text = stringResource(R.string.otp_message),
                fontSize = BwDimensions.FONT_8,
                fontWeight = FontWeight.Light,
                color = onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
            CommonOutlineTextField(
                dummyText = stringResource(R.string.enter_mobile),
                keyboardType = KeyboardType.Phone,
                onValueChange = {
                    loginViewModel.onEvent(LoginFormEvent.MobileChanged(it))
                },

                value = state.mobileNumber,
                prefixName = "+91",
                modifier = Modifier.onFocusEvent { evnt ->
                    if (evnt.isFocused) {
                        scope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }

                    }
                },
                imeAction = ImeAction.Done,
            )
            if (state.mobileNumberError != null) {
                CommonText(
                    text = state.mobileNumberError,
                    fontSize = BwDimensions.FONT_12,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = BwDimensions.PADDING_10)
                )
            }
            Spacer(Modifier.height(BwDimensions.PADDING_8))

            CommonOutlineTextField(
                dummyText = stringResource(R.string.enter_name),
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    loginViewModel.onEvent(LoginFormEvent.FirstNameChanged(it))
                },
                value = state.firstName,
                modifier = Modifier.onFocusEvent { evnt ->
                    if (evnt.isFocused) {
                        scope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }

                    }
                },
                imeAction = ImeAction.Done,
                keyboardActions = { focusManager.clearFocus() }
            )
            if (state.firstNameError != null) {
                CommonText(
                    text = state.firstNameError,
                    fontSize = BwDimensions.FONT_12,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = BwDimensions.PADDING_10)
                )
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = state.checkBox,
                    onCheckedChange = { newCheckedValue ->
                        loginViewModel.onEvent(LoginFormEvent.CheckBoxChanged(newCheckedValue))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = onPrimary,
                        checkmarkColor = colorResource(id = R.color.white),
                        uncheckedColor = onPrimary,
                    ),
                )
                HyperlinkText(
                    modifier = Modifier.weight(1f),
                    fullText = stringResource(R.string.terms_condition),
                    linkText = listOf("privacy policy", "terms & conditions"),
                    fontSize = BwDimensions.FONT_12,
                    hyperlinks = listOf(
                        "http://18.118.3.109/privacy_policy.html",
                        "http://18.118.3.109/term_and_conditions.html"
                    ),
                    onClick = {
                        // Handle link click
                    }
                )
            }
            if (!state.checkBox) {
                state.checkBoxError?.let {
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
            Spacer(Modifier.height(BwDimensions.SPACING_10))
            CommonButton(
                text = stringResource(R.string.continues) ,
                onClick = {
                    loginViewModel.onEvent(LoginFormEvent.Continue)
                },
                modifier = Modifier
                    .bringIntoViewRequester(bringIntoViewRequester)
                    .padding(paddingValues)
            )

        }
    }

}

@Composable
fun ConnectivityStatus(isConnected: Boolean) {
    var visibility by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        ConnectivityStatusBox(isConnected = isConnected)
    }
    LaunchedEffect(isConnected) {
        if (!isConnected) {
            visibility = true
        } else {
            delay(1000)
            visibility = false
        }
    }
}




