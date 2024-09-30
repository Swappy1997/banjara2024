package com.example.banjaraworld.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonText

@Composable
fun ErrorText(errorMessage: String?) {
    AnimatedVisibility(
        visible = errorMessage != null,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        errorMessage?.let {
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