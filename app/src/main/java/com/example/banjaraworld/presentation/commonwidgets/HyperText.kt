package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun HyperlinkText(
    modifier: Modifier,
    fullText: String,
    linkText: List<String>,
    linkTextColor: Color = Color(0xFF0566EC),
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    hyperlinks: List<String> = listOf("", ""),
    fontSize: TextUnit = TextUnit.Unspecified,
    flag: Boolean = false,
    onClick: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        addStyle(
            style = SpanStyle(
                color = Color(0xFF666565),
                fontSize = fontSize,
                fontWeight = FontWeight(400),
            ),
            start = 0,
            end = fullText.length
        )
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length


            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }

    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            if (flag) {
                onClick.invoke()
            } else {
                annotatedString
                    .getStringAnnotations("URL", it, it)
                    .firstOrNull()?.let { stringAnnotation ->
                        uriHandler.openUri(stringAnnotation.item)
                    }
            }

        }
    )

}
