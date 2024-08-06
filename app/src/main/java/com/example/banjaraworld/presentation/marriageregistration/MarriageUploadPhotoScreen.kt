package com.example.banjaraworld.presentation.marriageregistration

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.banjaraworld.R
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.ui.theme.background

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LinearDeterminateIndicator(0.2f)
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
        Spacer(modifier = Modifier.weight(1f))
    }
    CommonButton(onClick = { onContinueClick.invoke() }, text = stringResource(R.string.continues))
}


@Composable
fun ImageContainer(imageUri: Uri?, onClick: () -> Unit, containerColor: Color) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(containerColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(model = it),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } ?: run {
            Text(
                text = "Upload Image",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}