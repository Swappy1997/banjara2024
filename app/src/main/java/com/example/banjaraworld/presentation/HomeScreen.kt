package com.example.banjaraworld.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.banjaraworld.navigation.Screen
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onBackground
import com.example.banjaraworld.ui.theme.onPrimary

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    var statusBarColor by remember { mutableStateOf(background) }

    LaunchedEffect(scrollState.isScrollInProgress) {
        statusBarColor = if (scrollState.isScrollInProgress) onPrimary else background
    }

    SetBarStatus(color = statusBarColor)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(modifier=Modifier.fillMaxSize().background(background), state = scrollState) {
            items(300) {
                Text(text = "Hello $it")
            }
        }
    }
}