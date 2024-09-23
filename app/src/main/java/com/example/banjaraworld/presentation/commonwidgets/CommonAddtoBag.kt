package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.ui.theme.onSecondary

@Composable
fun CommonAddToBag(modifier: Modifier = Modifier, onclick: () -> Unit) {

    Button(
        modifier = Modifier,
        shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS),
        onClick = {
            onclick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = onSecondary)
    ) {
        Icon(
            imageVector = Icons.Outlined.ShoppingBag,
            contentDescription = "Bag",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(BwDimensions.PADDING_4))
        CommonText(
            text = "Add To Bag",
            fontSize = BwDimensions.FONT_11,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
        )
    }
}