import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Boy
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.R
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar
import com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage.MarriageFirstScreenEvent
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarriageHomeScreen(modifier: Modifier = Modifier) {
    var scrollState = rememberLazyGridState()
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        item {
            CommonAppBar(
                text = "",
                onCartClick = { /*TODO*/ },
                onBackClick = { /*TODO*/ },
                onFavoriteClick = { /*TODO*/ },
                scrollState = scrollState,
                showSearchIcon = true,
                showCartIcon = false,
            )

        }
        item {
            CommonText(
                text = "Filter By",
                fontSize = BwDimensions.FONT_14,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
        item {
            LazyRow {
             item {
                    FilterCard(
                        text = "Education",
                        icon = painterResource(id = R.drawable.shop)
                    )
                 FilterCard(
                     text = "State",
                     icon = painterResource(id = R.drawable.outline_home_24)
                 )
                 FilterCard(
                     text = "Lastname",
                     icon = painterResource(id = R.drawable.man)
                 )
                 FilterCard(
                     text = "Marital Status ",
                     icon = painterResource(id = R.drawable.wedding)
                 )


            }
            }
        }
        items(15) {

            MarriageCardWithOverlay()

        }

    }
}

@Composable
fun MarriageCardWithOverlay() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_8),
        shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            ) {
                // Image
                Image(
                    painter = painterResource(id = R.drawable.sheet),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                )

                // Black fade gradient overlay at the bottom
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 0f,
                                endY = 650f
                            )
                        )
                )

                // Text content on top of the image, aligned to the bottom
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    CommonText(
                        text = "S Rathod",
                        fontSize = BwDimensions.FONT_14,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    CommonText(
                        text = "Software Engineer",
                        fontSize = BwDimensions.FONT_11,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    CommonText(
                        text = "26 Years",
                        fontSize = BwDimensions.FONT_11,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        CommonText(
                            text = "Mumbai MH",
                            fontSize = BwDimensions.FONT_11,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
                }
                TextButton(onClick = { /*TODO*/ }) {
                    CommonText(
                        text = "Connect Now",
                        fontSize = BwDimensions.FONT_14,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
        }
    }


}

@Preview
@Composable
fun PreviewMarriageCardWithOverlay() {
    MarriageCardWithOverlay()
}


@Composable
fun FilterCard(modifier: Modifier = Modifier, text: String = "", icon: Painter) {
    AssistChip(
        onClick = {
        },
        label = {
            CommonText(
                text = text,
                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
            )
        },
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = "male",
                tint = onSecondary, modifier = Modifier.size(20.dp)
            )
        },
        modifier = modifier.padding(BwDimensions.PADDING_4),
        colors = AssistChipDefaults.assistChipColors(containerColor = Color.White),
        border = null,
        elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
    )
}