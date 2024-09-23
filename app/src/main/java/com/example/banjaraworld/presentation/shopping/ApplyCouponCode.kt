package com.example.banjaraworld.presentation.shopping

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.R
import com.example.banjaraworld.ui.theme.darkGreen
import com.example.banjaraworld.ui.theme.onPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyCouponCodeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyGridState()
    Box {
        Column {

            CommonAppBar(
                text = "Apply coupon",
                onCartClick = { /*TODO*/ },
                onBackClick = { /*TODO*/ },
                onFavoriteClick = { /*TODO*/ },
                scrollState = scrollState,
                showFavoriteIcon = false,
                showCartIcon = false
            )
            LazyColumn {
                item {
                    CommonText(
                        text = "Applicable",
                        fontSize = BwDimensions.TITTLE_FONT_SIZE,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
                item {
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                    OfferCouponCode()
                }
            }
        }
    }
}

@Composable
fun OfferCouponCode() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White), shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BwDimensions.PADDING_8)
        ) {
            Row {
                CommonText(
                    text = "New1000",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                CommonText(
                    text = stringResource(id = R.string.price_format),
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = darkGreen,
                    fontWeight = FontWeight.Bold
                )

            }
            Row {
                CommonText(
                    text = "Get Flat Rs 1000 Off on cart value of 3990 & above",
                    fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(2f),
                    maxline = 3, textAlign = TextAlign.Start
                )

                CommonText(
                    text = "Apply coupon",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = onPrimary,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f),
                    textDecoration = TextDecoration.Underline
                )

            }

        }
    }
}
