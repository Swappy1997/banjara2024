package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.banjaraworld.ui.theme.onSecondary


@Composable
fun LinearDeterminateIndicator(progressValue: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier.weight(0.1f))

        LinearProgressIndicator(
            progress = progressValue,
            trackColor = onSecondary,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .height(8.dp)
                .wrapContentWidth()
                .weight(0.8f)
        )

    }
}