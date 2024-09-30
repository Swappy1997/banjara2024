package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary


@Composable
fun CommonOutlineTextField(
    modifier: Modifier,
    dummyText: String,
    value: String? = "",
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit = {},
    prefixName: String? = "",
    keyboardActions: () -> Unit = {},
    readOnly: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        elevation = CardDefaults.cardElevation(BwDimensions.ELEVATION_HEIGHT),

        ) {
        TextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
                capitalization = KeyboardCapitalization.Sentences,  // Capitalize the first letter

            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardActions()
                }
            ),
            textStyle = MaterialTheme.typography.labelMedium.copy(
                fontSize = BwDimensions.FONT_14, color = Color(0xFF515151)
            ),
            singleLine = true,
            value = value ?: "",
            onValueChange = {
                onValueChange(it) // Pass the value to the provided lambda
            },
            readOnly = readOnly,

            placeholder = {
                Text(
                    dummyText, style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = BwDimensions.FONT_14, color = Color(0xFFC0CBD8)
                    ), textAlign = TextAlign.Center
                )
            },
            prefix = {
                if (prefixName != null) {
                    Text(prefixName)
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = onPrimary,
                cursorColor = onSecondary,
            ),

            shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS),
            modifier = Modifier
                .fillMaxWidth()
                .height(BwDimensions.TextFieldHeight),
        )
    }

}