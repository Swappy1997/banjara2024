package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OtpInput(
    modifier: Modifier = Modifier, otpValue: String, onValueChange: (String) -> Unit = {},
) {

    BasicTextField(value = otpValue, onValueChange = {
        if (otpValue.length <= 6) {
            onValueChange(it)
        }
    }, singleLine = true, keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.NumberPassword,
    ),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {

                repeat(6) { index ->
                    val char = when {
                        index >= otpValue.length -> ""
                        else -> otpValue[index].toString()
                    }
                    val isFocused = otpValue.length == index

                    Text(
                        modifier = Modifier
                            .width(40.dp)
                            .border(
                                if (isFocused) 2.dp else 1.dp,
                                if (isFocused) Color.DarkGray else Color.LightGray,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp),
                        text = char, textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.width(8.dp))

                }
            }
        })

}