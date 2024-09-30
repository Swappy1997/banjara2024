import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.common.utils.Utils.formatText
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.ui.theme.PoppinsFont
import com.example.banjaraworld.ui.theme.onPrimary

@Composable
fun MarriageUploadPhotoScreen(modifier: Modifier = Modifier, onContinueClick: () -> Unit) {
    var imageUri1 by remember { mutableStateOf<Uri?>(null) }
    var imageUri2 by remember { mutableStateOf<Uri?>(null) }
    var imageUri3 by remember { mutableStateOf<Uri?>(null) }

    var selectedImageIndex by remember { mutableStateOf(0) } // Track which image container is clicked

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            when (selectedImageIndex) {
                1 -> imageUri1 = it
                2 -> imageUri2 = it
                3 -> imageUri3 = it
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
                .fillMaxSize()
                .padding(BwDimensions.PADDING_8),
            verticalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                LinearDeterminateIndicator(0.6f)
            }

            item {

                Text(
                    text = formatText(
                        prefix = "It's all about \n",
                        discountColor = onPrimary,
                        formattedText = "presentation",
                    ), fontSize = BwDimensions.FONT_20, fontFamily = PoppinsFont
                )

            }

            item {
                Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
            }
            // ImageUpload containers
            item {
                Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    ImageUpload(imageUri = imageUri1, onClick = {
                        selectedImageIndex = 1 // Track that the first image container was clicked
                        pickImageLauncher.launch("image/*")
                    }, modifier = Modifier.size(150.dp) // Adjust size as needed
                        , onClearClick = {
                            imageUri1 = null
                        })

                    Spacer(modifier = Modifier.size(16.dp))

                    ImageUpload(imageUri = imageUri2, onClick = {
                        selectedImageIndex = 2 // Track that the second image container was clicked
                        pickImageLauncher.launch("image/*")
                    }, modifier = Modifier.size(150.dp) // Adjust size as needed
                        , onClearClick = {
                            imageUri2 = null
                        })
                }

            }

            item {
                BulletPointText("Upload at least 1 photo")
                BulletPointText("Show us that smile please")
                BulletPointText("Stay clear of inappropriate content")
                BulletPointText("Avoid blurry pictures")
            }
            item {
                Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
            }
        }

        CommonButton(
            onClick = { onContinueClick() },
            text = stringResource(R.string.continues),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ImageUpload(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    onClick: () -> Unit,
    onClearClick: () -> Unit // Callback to handle clearing the image
) {
    Box(modifier = modifier
        .clickable { onClick() }
        .background(Color.White), // Add a background color to visualize the container
        contentAlignment = Alignment.Center) {
        // Show image if available, otherwise show the add icon
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(model = it),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Show the close (clear) icon only when the image is displayed
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(BwDimensions.PADDING_4),
                contentAlignment = Alignment.BottomEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(BwDimensions.PADDING_2)
                        .background(Color.White.copy(alpha = 0.2f), shape = CircleShape)
                        .padding(BwDimensions.PADDING_2)
                        .clickable { onClearClick() }, // Call onClearClick when clear icon is clicked
                )
            }
        } ?: run {
            // Show the add icon if no image is present
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add",
                tint = Color.Gray,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}


@Composable
fun BulletPointText(text: String) {
    Row(verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()) {
        CommonText(
            text = "\u2022", // Bullet point character
            fontSize = BwDimensions.FONT_14,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(end = 2.dp) // Padding after bullet point
        )
        CommonText(
            text = text,
            fontSize = BwDimensions.FONT_8,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
    }
}
