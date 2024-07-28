package com.example.banjaraworld.presentation.splash_screen

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.banjaraworld.R
import com.example.banjaraworld.navigation.AuthScreen
import com.example.banjaraworld.presentation.SetBarStatus
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier,navController: NavController) {

    var splashScreenFinished by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        // Introduce a delay of 2000 milliseconds (2 seconds)
        delay(1000)
        navController.navigate(AuthScreen.Login.route)

        //  var logged =sessionManger.getLoggedINStatus()
//        splashScreenFinished = true
//        if (logged == true) {
//            navController.navigate(Screen.HomeScreen.route)
//
//        } else {
//        }
    }
    if (!splashScreenFinished) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background),
            contentAlignment = Alignment.Center,
        ) {
            SetBarStatus(background)
            AsyncImage(
                model = R.drawable.logo,
                contentDescription = "logo",
                modifier = Modifier.size(400.dp)
            )

        }
    }


}