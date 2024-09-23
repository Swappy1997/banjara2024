package com.example.banjaraworld.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.ui.theme.darkGreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    var statusBarColor by remember { mutableStateOf(background) }

    LaunchedEffect(scrollState.isScrollInProgress) {
        statusBarColor = if (scrollState.isScrollInProgress) onPrimary else background
    }

    SetBarStatus(color = statusBarColor)
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 50.dp)
                .background(background),
            state = scrollState
        ) {

            item {
                MatrimoneyBannerCard()
            }
            item {
                ShoppingBannerCard()
            }
            item {
                AllOfferCouponCode()
            }
        }
    }
}

@Composable
fun AllOfferCouponCode() {

}
@Composable
fun ShoppingBannerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(250.dp),
        border = CardDefaults.outlinedCardBorder(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Top Row with Shop Icon, Text, and Arrow Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = "store",
                    tint = Color.Gray
                )
                CommonText(
                    text = "Shop",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                CommonText(
                    text = "> Trending Now",
                    fontSize = 11.sp,
                    color = Color(0xFF32CD32),  // Dark green color
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = "arrow",
                    tint = Color.Gray,
                    modifier = Modifier
                        .background(shape = CircleShape, color = Color(0xFFF5F5F5))
                        .padding(4.dp)
                )
            }

            // Horizontal Scrollable Row with Overlapping Cards
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy((-40).dp) // Negative spacing for overlap
                ) {
                    items(5) { index -> // Adjust the number of items based on your needs
                        Card(
                            modifier = Modifier
                                .width(150.dp)
                                .height(200.dp)
                                .zIndex(index.toFloat()), // Ensures that cards overlap in the right order
                            border = CardDefaults.outlinedCardBorder(),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            AsyncImage(
                                model = R.drawable.sheet,  // Replace with your actual image
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MatrimoneyBannerCard() {

    CommonBannerCard(text = "Explore Matrimony")
}

@Composable
fun CommonBannerCard(text: String) {
    val images = listOf(R.drawable.sheet, R.drawable.sheet, R.drawable.sheet)
    val pagerState = rememberPagerState(pageCount = { images.size })

    // LaunchedEffect to automatically slide pages every 1 second
    LaunchedEffect(pagerState) {
        while (true) {
            yield() // To ensure smooth transitions and avoid blocking the main thread
            delay(3000) // Wait for 1 second
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White), shape = RectangleShape,
        border = CardDefaults.outlinedCardBorder()
    ) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            modifier = Modifier.fillMaxWidth()
        ) { index ->
            Box {
                AsyncImage(
                    model = images[index],
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                CommonButton(
                    text = text,
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(180.dp)
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}

