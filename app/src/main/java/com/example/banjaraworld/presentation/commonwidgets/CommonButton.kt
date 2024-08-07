package com.example.banjaraworld.presentation.commonwidgets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banjaraworld.ui.theme.Purple40
import com.example.banjaraworld.ui.theme.buttonShadow
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary


@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                    spotColor = Purple40,
                ambientColor = Purple40,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),

//        shape = RoundedCornerShape(12.dp),
//        elevation = ButtonDefaults.buttonElevation(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = onSecondary),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp, color = Color.White)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CommonButtonPreview() {

    CommonButton(text = "Continue", onClick = {}, modifier = Modifier.padding(10.dp))

}