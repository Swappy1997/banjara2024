package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.banjaraworld.common.utils.BwDimensions

@Composable
fun SectionTitle(text: String) {
    CommonText(
        text = text,
        fontSize = BwDimensions.TITTLE_FONT_SIZE,
        color = Color.Black,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start

    )
}