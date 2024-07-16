package com.example.banjaraworld.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.banjaraworld.presentation.login_screen.LoginScreen
import com.example.banjaraworld.presentation.splash_screen.SplashScreen
import com.example.banjaraworld.presentation.verfication_screen.VerficationScreen
import com.example.banjaraworld.ui.theme.BanjaraWorldTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            BanjaraWorldTheme {
                //SplashScreen()
                LoginScreen()
                //VerficationScreen()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, inset ->
            val bottom = inset.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            inset

        }
    }
}

@Composable
fun SetBarStatus(color: Color) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(key1 = true) {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
        }
    }
}