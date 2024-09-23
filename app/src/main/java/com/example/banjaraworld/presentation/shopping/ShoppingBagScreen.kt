package com.example.banjaraworld.presentation.shopping

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Celebration
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingBagScreen(
    modifier: Modifier = Modifier,
    goToWishlist: () -> Unit,
    goToApplyCouponCode: () -> Unit
) {
    val scrollState = rememberLazyGridState()

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {

        Column(modifier = modifier) {
            CommonAppBar(
                text = "Bag(Product 1)",
                showCartIcon = false,
                showSearchIcon = false,
                onCartClick = { /* TODO */ },
                onBackClick = { /* TODO */ },
                onFavoriteClick = { goToWishlist() },
                scrollState = scrollState
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 120.dp),
            ) {
                item { DefultAddressCard() }
                item {
                    BagItemCard()
                    BagItemCard()
                    BagItemCard()
                    BagItemCard()
                    BagItemCard()
                    BagItemCard()
                }
                item {
                    CouponCodeCard(goToApplyCouponCode)
                }
                item {
                    OrderDetailsCard(goToApplyCouponCode)
                }
                item {
                    ReturnAndRefundCard()
                }
                item {
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                }

            }
        }
        YouSaveCard()
        ProccedToPayment()
    }
}

@Composable
fun YouSaveCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BwDimensions.PADDING_8, bottom = 80.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(onPrimary.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BwDimensions.PADDING_4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Celebration,
                contentDescription = "celebration",
                tint = onPrimary
            )
            CommonText(
                text = "Cheers!",
                fontSize = BwDimensions.FONT_11,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            CommonText(
                text = "You save Rs 555 on this order",
                fontSize = BwDimensions.FONT_11,
                color = onPrimary,
                fontWeight = FontWeight.Bold
            )

        }
    }
}

@Composable
fun ReturnAndRefundCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BwDimensions.PADDING_8),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BwDimensions.PADDING_8)
        ) {
            CommonText(
                text = "Return/Refund policy",
                fontSize = BwDimensions.TITTLE_FONT_SIZE,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            CommonText(
                text = "In case of return or refund policy ,we ensure quick and easy return/refund process.Full amount will be refunded excluding Convenience Fee",
                fontSize = BwDimensions.FONT_8,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.wrapContentSize(),
                maxline = 3,
                textAlign = TextAlign.Start
            )
            CommonText(
                text = "Read policy",
                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                color = onPrimary,
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
            )
        }
    }
}

@Composable
fun OrderDetailsCard(goToApplyCouponCode: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BwDimensions.PADDING_8),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier.padding(BwDimensions.PADDING_8)
        ) {
            CommonText(
                text = "Order Details",
                fontSize = BwDimensions.TITTLE_FONT_SIZE,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = BwDimensions.PADDING_8)
            )

            // Table-like view
            OrderDetailItem(label = "Bag Total", value = "Rs 3333")
            OrderDetailItem(label = "Bag Saving", value = " -Rs 555", color = darkGreen)
            OrderDetailItem(
                label = "Coupon Saving",
                value = "Apply Coupon",
                color = onPrimary,
                onClick = goToApplyCouponCode)
            OrderDetailItem(label = "Delivery Fee", value = "Free", color = darkGreen)
            OrderDetailItem(label = "Platform Fee", value = "Rs 28")
            OrderDetailItem(
                label = "Amount Payable",
                value = "Rs 555",
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun OrderDetailItem(
    label: String,
    value: String,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = BwDimensions.PADDING_4),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CommonText(
            text = label,
            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
            color = Color.Black,
            fontWeight = fontWeight
        )
        CommonText(
            text = value,
            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
            color = color,
            fontWeight = fontWeight,
            modifier = Modifier.clickable {
                onClick()
            }
        )
    }
}

@Composable
fun CouponCodeCard(goToApplyCouponCode: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BwDimensions.PADDING_8),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BwDimensions.PADDING_8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_4)
        ) {
            Icon(imageVector = Icons.Outlined.Discount, contentDescription = "discount")
            CommonText(
                text = "Apply Coupon Code",
                fontSize = BwDimensions.FONT_11,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1f))
            CommonText(
                text = "Select",
                fontSize = BwDimensions.FONT_11,
                color = onPrimary,
                fontWeight = FontWeight.Medium, textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    goToApplyCouponCode()
                }
            )

        }
    }
}

@Composable
fun ProccedToPayment() {
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
                .fillMaxWidth()
                .padding(BwDimensions.PADDING_8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CommonText(
                text = stringResource(id = R.string.price_format),
                fontSize = BwDimensions.FONT_11,
                color = onSecondary,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = onSecondary),
                shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS),
            ) {
                CommonText(
                    text = "Proceed to Payment",
                    fontSize = BwDimensions.FONT_11,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun BagItemCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BwDimensions.PADDING_8),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = "Remove item",
                    tint = Color.Gray
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = R.drawable.sheet,
                    contentDescription = "Item image",
                    modifier = Modifier.weight(1f), contentScale = ContentScale.Inside
                )
                Column(
                    modifier = Modifier
                        .padding(BwDimensions.PADDING_8)
                        .weight(2f)
                ) {
                    CommonText(
                        text = "Banjara T Shirt",
                        fontSize = BwDimensions.TITTLE_FONT_SIZE,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                    CommonText(
                        text = "Designed by Banjara World",
                        fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = BwDimensions.PADDING_4),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SizeCardBag()
                        var quantity = remember { mutableStateOf(1) }.value

                        QuantityControlCard(
                            quantity = quantity,
                            onIncrement = { quantity++ },
                            onDecrement = { quantity-- }
                        )
                    }
                    PriceDetails()
                    DeliveryInfo()
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    WishlistCard()
                }
            }
        }
    }
}

@Composable
fun SizeCardBag() {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = background),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonText(
                text = "Size: ",
                fontSize = BwDimensions.FONT_8,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )
            CommonText(
                text = "XXL",
                fontSize = BwDimensions.FONT_10,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Expand size options",
                tint = onSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun PriceDetails() {
    Row {
        CommonText(
            text = stringResource(id = R.string.price_format),
            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
        CommonText(
            text = stringResource(id = R.string.price_format),
            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.LineThrough
        )
    }
    CommonText(
        text = "Saved ${stringResource(id = R.string.price_format)}",
        fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
        color = darkGreen,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun DeliveryInfo() {
    Row {
        Icon(
            imageVector = Icons.Default.DeliveryDining,
            contentDescription = "Delivery info"
        )
        CommonText(
            text = "Delivery by Sun 1 Sep",
            fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
            color = darkGreen,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun WishlistCard() {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .padding(BwDimensions.PADDING_8),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = CardDefaults.outlinedCardBorder(),
        shape = RoundedCornerShape(2.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(BwDimensions.PADDING_4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Move to wishlist"
            )
            CommonText(
                text = "Move to Wishlist",
                fontSize = BwDimensions.FONT_14,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun DefultAddressCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = onSecondary.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BwDimensions.PADDING_8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = onSecondary,
                modifier = Modifier
                    .size(25.dp)
                    .padding(end = 8.dp)
            )
            CommonText(
                text = "Gavankar nagar civil line near police ground washim 444505",
                fontSize = BwDimensions.FONT_10,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Expand",
                tint = onSecondary,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun QuantityControlCard(
    quantity: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentSize(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = background)
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = BwDimensions.PADDING_8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_8)
        ) {
            IconButton(
                onClick = onDecrement,
                modifier = Modifier.size(25.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Decrement",
                    tint = onSecondary
                )
            }
            CommonText(
                text = quantity.toString(),
                fontSize = BwDimensions.FONT_14,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            IconButton(
                onClick = onIncrement,
                modifier = Modifier.size(25.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increment",
                    tint = onSecondary
                )
            }
        }
    }
}
