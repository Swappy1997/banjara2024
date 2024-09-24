package com.example.banjaraworld.common.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.banjaraworld.ui.theme.darkGreen

object Utils {

    fun showToast(message: String?, context: Context?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    fun shareContent(context: Context, title: String, text: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val chooser = Intent.createChooser(shareIntent, title)
        context.startActivity(chooser)
    }

    fun formatDiscountText(
        discount: String,
        prefix: String = "Save upto \n",
        suffix: String = " today!",
        discountColor: Color = darkGreen,
        discountWeight: FontWeight = FontWeight.Bold
    ) = buildAnnotatedString {
        append(prefix)
        withStyle(
            style = SpanStyle(
                color = discountColor,
                fontWeight = discountWeight,
                fontSize = BwDimensions.FONT_20
            )
        ) {
            append(discount)
        }
        append(suffix)
    }

}