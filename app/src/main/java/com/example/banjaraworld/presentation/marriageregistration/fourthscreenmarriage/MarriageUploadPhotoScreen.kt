package com.example.banjaraworld.presentation.marriageregistration.fourthscreenmarriage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.ui.theme.onPrimary

@Composable
fun MarriageUploadPhotoScreen(modifier: Modifier = Modifier, onContinueClick: () -> Unit) {

    var imageUri1 by remember { mutableStateOf<Uri?>(null) }
    var imageUri2 by remember { mutableStateOf<Uri?>(null) }
    var imageUri3 by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            when {
                imageUri1 == null -> imageUri1 = it
                imageUri2 == null -> imageUri2 = it
                imageUri3 == null -> imageUri3 = it
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(BwDimensions.PADDING_8)
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize().padding(BwDimensions.PADDING_8),
            verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                LinearDeterminateIndicator(0.6f)

            }
            item {

                ImageContainer(
                    imageUri = imageUri1,
                    onClick = { pickImageLauncher.launch("image/*") },
                    containerColor = Color.LightGray
                )
                ImageContainer(
                    imageUri = imageUri2,
                    onClick = { pickImageLauncher.launch("image/*") },
                    containerColor = Color.Gray
                )
                ImageContainer(
                    imageUri = imageUri3,
                    onClick = { pickImageLauncher.launch("image/*") },
                    containerColor = Color.DarkGray
                )
            }
        }
        CommonButton(
            onClick = { onContinueClick.invoke() }, text = stringResource(R.string.continues),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun ImageContainer(imageUri: Uri?, onClick: () -> Unit, containerColor: Color) {

    Card(
        modifier = Modifier
            .size(300.dp),
        border = BorderStroke(BwDimensions.ELEVATION_HEIGHT, onPrimary),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Inside
                )
            } ?: run {
                CommonText(
                    text = "Upload Image",
                    fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )

            }
        }
    }
}



