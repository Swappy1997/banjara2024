package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.example.banjaraworld.ui.theme.PoppinsFont


@Composable
fun CommonText(
    text: String,
    fontSize: TextUnit,
    color: Color,
    fontWeight: FontWeight,
    overflow: TextOverflow? = TextOverflow.Visible,
    onClick: (() -> Unit)? = null,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier,
    textDecoration: TextDecoration = TextDecoration.None,
    maxline: Int = 1
) {
    val textModifier = if (onClick != null) {
        modifier.clickable { onClick.invoke() }
    } else {
        modifier
    }
    if (overflow != null) {
        Text(
            text = text,
            fontSize = fontSize,
            color = color,
            fontWeight = fontWeight,
            overflow = overflow,
            modifier = textModifier,
            textAlign = textAlign,
            fontFamily = PoppinsFont,
            textDecoration = textDecoration,
            maxLines = maxline,
            lineHeight = TextUnit.Unspecified,

        )
    }
}