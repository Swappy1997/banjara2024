package com.example.banjaraworld.presentation.splash_screen

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.banjaraworld.R
import com.example.banjaraworld.presentation.SetBarStatus
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onBackground

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {

    Box(modifier = Modifier.fillMaxSize().background(background), contentAlignment = Alignment.Center,) {
        SetBarStatus(background)
        AsyncImage(
            model = R.drawable.logo,
            contentDescription = "logo",
            modifier = Modifier.size(400.dp)
        )
    }
}