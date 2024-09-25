package com.example.banjaraworld.presentation.marriageregistration.eightmarriagescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonText

@Composable
fun MarriageHobbiesAndInterestsScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(BwDimensions.PADDING_8)
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_8)
        ) {
            item {
                LinearProgressIndicator(progress = { 1f })
            }
            item {
                CommonText(
                    text = "Please lets us know about your hobbies and interest",
                    fontSize = BwDimensions.FONT_14,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        CommonButton(
            text = "Next",
            onClick = { /*TODO*/ },
            modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}