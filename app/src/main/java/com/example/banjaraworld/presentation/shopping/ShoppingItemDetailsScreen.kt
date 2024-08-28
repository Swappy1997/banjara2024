package com.example.banjaraworld.presentation.shopping

import android.content.Intent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.Product
import com.example.banjaraworld.presentation.StarRating
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.darkGreen
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingDetails(
    modifier: Modifier = Modifier,
    link: String? = null,
    onclick: () -> Unit,
    onAddtoBagClick: () -> Unit
) {

    val images = listOf(R.drawable.sheet, R.drawable.sheet, R.drawable.sheet)
    val pagerState = rememberPagerState(pageCount = { images.size })
    val cartCount = remember { mutableStateOf(0) }
    val scrollState = rememberLazyGridState()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(background),
        contentAlignment = Alignment.BottomCenter // Align content to the bottom center
    ) {
        Column {
            ShoppingAppBar(cartCount, scrollState)
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 80.dp)
            ) {
                item {
                    ShoppingPager(images, pagerState, screenWidth)
                }
                item {
                    LikeCountCard()
                }
                item {
                    ProductDetails()
                }
                item {
                    OfferCards()
                }
                item {
                    ColorCard()
                }
                item {
                    SizeCard()
                }
                item {
                    DeliveryAddressDetail()
                }
                item {
                    RatingDetail()
                }
                item {
                    SimilarProduct()
                }

            }
        }

        ShareLikeAndCartCard(
            productPrice = "222",
            productLink = "http://www.banjaraworld.com/22",
            productName = "Banjara T shirt",
            onAddtoBagClick = onAddtoBagClick
        )

    }
}

@Composable
fun SimilarProduct() {
    val productList = listOf(
        Product("Swapppppp ", 10.00, R.drawable.sheet),
        Product("Swapppppp ", 10.00, R.drawable.sheet),
        Product("Swapppppp ", 10.00, R.drawable.woman),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        // Add more products here...
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CommonText(
                text = "Similar to above item",
                fontSize = BwDimensions.FONT_14,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = BwDimensions.PADDING_8),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                items(productList.size) { rowItems ->
                    SimilarProductItem(product = productList[rowItems]) {

                    }
                }
            }
        }

    }

}


@Composable
fun SimilarProductItem(product: Product, onClick: () -> Unit) {
    var isLiked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(BwDimensions.PADDING_4)
            .wrapContentWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = "Product Image",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .border(
                    1.dp,
                    shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS),
                    color = Color.LightGray
                )
                .clickable(onClick = { onClick() },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(BwDimensions.PADDING_4))

        CommonText(
            text = product.name,
            fontSize = BwDimensions.TITTLE_FONT_SIZE,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.width(150.dp),
            overflow = TextOverflow.Ellipsis,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BwDimensions.SPACING_5)
        ) {
            CommonText(
                text = stringResource(id = R.string.price_format, product.price),
                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            CommonText(
                text = stringResource(id = R.string.price_format, product.price),
                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textDecoration = TextDecoration.LineThrough
            )
            CommonText(
                text = "35% off",
                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                color = darkGreen,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = R.drawable.greendiscount,
                contentDescription = "Discount",
                modifier = Modifier.size(20.dp)
            )
            CommonText(
                text = "Offer price ${stringResource(id = R.string.price_format, product.price)}",
                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                color = darkGreen,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(BwDimensions.PADDING_4))
        StarRating(rating = 1.5f) {}
    }
}


@Composable
fun ShareLikeAndCartCard(
    productName: String,
    productPrice: String,
    productLink: String?,
    onAddtoBagClick: () -> Unit
) {
    val context = LocalContext.current
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        OpenItemSizeSheet(
            showBottomSheet = true,
            onDismissRequest = { showBottomSheet = false },
            onAddToBagClick = onAddtoBagClick,
            onSizeSelected = {
                showBottomSheet = false
            }
        )
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(
                elevation = 10.dp,
                shape = RectangleShape,
                spotColor = onSecondary,
                ambientColor = onSecondary
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = BwDimensions.PADDING_4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    // Share functionality
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Check out this product: $productName\nPrice: $productPrice\n$productLink"
                        )
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share Product"))
                },
                modifier = Modifier
                    .size(20.dp)
                    .background(
                        color = background,
                        shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS)
                    )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "share",
                    tint = Color.Gray
                )
            }

            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .size(20.dp)
                    .background(
                        color = background,
                        shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS)
                    )
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "share",
                    tint = Color.Gray
                )
            }
            Button(
                modifier = Modifier,
                shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS),
                onClick = {
                    showBottomSheet = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = onSecondary)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingBag,
                    contentDescription = "Bag",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(BwDimensions.PADDING_4))
                CommonText(
                    text = "Add To Bag",
                    fontSize = BwDimensions.FONT_11,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenItemSizeSheet(
    showBottomSheet: Boolean,
    onDismissRequest: () -> Unit,
    onSizeSelected: (String) -> Unit,
    onAddToBagClick: () -> Unit
) {
    if (showBottomSheet) {
        SizeSelectionBottomSheet(
            onSizeSelected = onSizeSelected,
            onDismissRequest = onDismissRequest,
            onAddToBagClick = onAddToBagClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SizeSelectionBottomSheet(
    onSizeSelected: (String) -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onAddToBagClick: () -> Unit,
) {
    ModalBottomSheet(
        shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS),
        containerColor = Color.White,
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Cross Icon for Dismiss
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = BwDimensions.PADDING_8),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(50)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close",
                        tint = Color.Gray
                    )
                }
            }

            // Title Text
            Text(
                text = "Select Size",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 8.dp)
            )

            // Size options (assuming SizeCard() is defined elsewhere)
            SizeCard()

            // Add to Bag Button
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Button(
                    onClick = {
                        onDismissRequest()
                        onAddToBagClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = BwDimensions.PADDING_12),
                    shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS),
                    colors = ButtonDefaults.buttonColors(containerColor = onSecondary)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = "Bag",
                        tint = Color.White
                    )
                    CommonText(
                        text = "Add To Bag",
                        fontSize = BwDimensions.FONT_11,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}


@Composable
fun RatingDetail() {
    val ratingListInFloat = listOf(1f, 0.9f, 0.3f, 0.5f, 0.4f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier.padding(horizontal = BwDimensions.PADDING_8)) {
            CommonText(
                text = "Ratings",
                fontSize = BwDimensions.FONT_14,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = BwDimensions.PADDING_8)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Column for average rating, star rating, and number of ratings
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    CommonText(
                        text = "4.0",
                        fontSize = BwDimensions.FONT_14,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
                    StarRating(rating = 4.0f, onRatingChange = {})
                    CommonText(
                        text = "27 Ratings",
                        fontSize = BwDimensions.FONT_8,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = BwDimensions.PADDING_4)
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(BwDimensions.PADDING_8),
                    color = Color.Gray
                )

                // Column for rating bars
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    ratingListInFloat.forEachIndexed { index, ratingValue ->
                        StatusBarForRating(ratingValue = ratingValue, ratingText = "${5 - index}")
                    }
                }
            }
        }
    }
}

@Composable
fun StatusBarForRating(modifier: Modifier = Modifier, ratingValue: Float, ratingText: String) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        CommonText(
            text = ratingText,
            fontSize = BwDimensions.FONT_8,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
        )

        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "star",
            tint = Color.Gray,
            modifier = Modifier.size(10.dp)
        )

        LinearProgressIndicator(
            progress = { ratingValue },
            modifier = Modifier
                .height(4.dp)
                .wrapContentWidth()
                .weight(1f),
            trackColor = onSecondary,
            strokeCap = StrokeCap.Round,
        )

        CommonText(
            text = "${(ratingValue * 100).toInt()}%",
            fontSize = BwDimensions.FONT_8,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = BwDimensions.PADDING_4)
        )
    }
}


@Composable
fun DeliveryAddressDetail() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BwDimensions.PADDING_8)
        ) {
            CommonText(
                text = "Delivery Address",
                fontSize = BwDimensions.FONT_14,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        BwDimensions.PADDING_8
                    ),
                colors = CardDefaults.cardColors(onPrimary.copy(alpha = 0.1f)),
                shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(BwDimensions.PADDING_8),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CommonText(
                        text = "444505",
                        fontSize = BwDimensions.FONT_11,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                    )
                    CommonText(
                        text = "Change Pincode",
                        fontSize = BwDimensions.FONT_11,
                        color = onSecondary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}

@Composable
fun SizeCard() {
    val sizeList = listOf("XS", "S", "M", "L", "XL")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),// Adjusted height for better visibility of all items
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape,

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            CommonText(
                text = "Size",
                fontSize = BwDimensions.FONT_11,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BwDimensions.PADDING_4),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_8)
            ) {
                items(sizeList.size) {
                    Card(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(BwDimensions.PADDING_4)
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(BwDimensions.ELEVATION_HEIGHT)
                            ),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RectangleShape,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(BwDimensions.PADDING_4),
                            contentAlignment = Alignment.Center
                        ) {
                            CommonText(
                                text = sizeList[it],
                                fontSize = BwDimensions.FONT_14,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                            )
                        }

                    }
                }
            }
        }
    }

}

@Composable
fun ColorCard() {

    val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Gray)
    val colorNames = listOf("Red", "Blue", "Green", "Yellow", "Gray")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = BwDimensions.PADDING_4), // Adjusted height for better visibility of all items
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape,

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            CommonText(
                text = "Color",
                fontSize = BwDimensions.FONT_11,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_8)
            ) {
                items(colors.size) { index ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        CommonText(
                            text = colorNames[index],
                            fontSize = BwDimensions.FONT_8,
                            color = colors[index],
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        AsyncImage(
                            model = R.color.ic_launcher_background,
                            contentDescription = colorNames[index],
                            modifier = Modifier
                                .size(30.dp)
                                .background(colors[index], CircleShape)
                        )

                    }
                }
            }
        }
    }
}


@Composable
fun OfferCards(modifier: Modifier = Modifier) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(BwDimensions.PADDING_4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = R.drawable.greendiscount,
                contentDescription = "offer",
                modifier = Modifier.size(20.dp)
            )
            CommonText(
                text = "Coupon",
                fontSize = BwDimensions.FONT_8,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
            )
            CommonText(
                text = "Offer Price${stringResource(id = R.string.price_format)}",
                fontSize = BwDimensions.FONT_11,
                color = darkGreen,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )
        }
    }

}


@Composable
fun ProductDetails(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(BwDimensions.PADDING_8)
    ) {

        CommonText(
            text = "Tittle Name",
            fontSize = BwDimensions.TITTLE_FONT_SIZE,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
        )
        CommonText(
            text = "Sub tittle name name",
            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
        )
        StarRating(rating = 4.5f, onRatingChange = {})
        CommonText(
            text = " MRP ${stringResource(id = R.string.price_format)}309",
            fontSize = BwDimensions.TITTLE_FONT_SIZE,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
        )
        CommonText(
            text = "Price inclusive of all taxes",
            fontSize = BwDimensions.FONT_10,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
        )
    }
}

@Composable
fun LikeCountCard(modifier: Modifier = Modifier) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = onSecondary.copy(alpha = 0.1f))

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "like",
                tint = Color.Red,
                modifier = Modifier.size(20.dp)
            )
            CommonText(
                text = "3.2 k shoppers wishlisted in last 30 days",
                fontSize = BwDimensions.FONT_10,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingAppBar(cartCount: MutableState<Int>, scrollState: LazyGridState) {
    CommonAppBar(
        text = "",
        onCartClick = { /*TODO*/ },
        onBackClick = { /*TODO*/ },
        cartCount = cartCount,
        scrollState = scrollState, onFavoriteClick = {}
    )
}

@Composable
fun ShoppingPager(
    images: List<Int>, pagerState: PagerState, screenWidth: Dp
) {
    HorizontalPager(
        state = pagerState, pageSize = PageSize.Fill, modifier = Modifier.fillMaxWidth()
    ) { index ->
        ShoppingCard(
            image = images[index], pagerState = pagerState, screenWidth = screenWidth
        )
    }
}

@Composable
fun ShoppingCard(
    image: Int, pagerState: PagerState, screenWidth: Dp
) {
    val dividerThickness = 1.dp
    val dividerOffset by animateDpAsState(
        targetValue = when (pagerState.currentPage) {
            0 -> 0.dp // Start for the first page
            1 -> (screenWidth - dividerThickness) / 3 // Center for the second page
            2 -> (screenWidth - dividerThickness) / 2 // Center for the second page
            else -> screenWidth - dividerThickness / 3 // End for the third page
        }, label = "DividerOffset"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {
        Column {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )

            ShoppingDivider(dividerOffset, screenWidth / 3, dividerThickness)
        }
    }
}

@Composable
fun ShoppingDivider(
    offset: Dp, width: Dp, thickness: Dp, color: Color = onSecondary
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(
            modifier = Modifier
                .width(width)
                .offset(x = offset)
                .padding(horizontal = 10.dp, vertical = 10.dp),
            thickness = thickness,
            color = color.copy(alpha = 1f)
        )
    }
}
