package com.example.banjaraworld.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.darkGreen
import com.example.banjaraworld.ui.theme.onPrimary

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(modifier: Modifier = Modifier, onclick: () -> Unit) {
    val cartCount = remember { mutableStateOf(0) } // Use `by` for state delegation
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scrollState = rememberLazyGridState()

    val productList = listOf(
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        Product("Swapppppp gajbgdbgnbngb", 10.00, R.drawable.sheet),
        // Add other products here
    )

    val categories = listOf(
        Category("Home", R.drawable.filled_home),
        Category("Sale", R.drawable.sale), Category("Men", R.drawable.man),
        Category("Women", R.drawable.woman),
        Category("Bazar", R.drawable.shop),
        Category("Books", R.drawable.shop),
    )
    var selectedCategoryIndex by remember { mutableStateOf(1) } // Track selected category

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            CommonAppBar(
                text = stringResource(id = R.string.banjara_bazar),
                onCartClick = { /* TODO */ },
                onBackClick = { /* TODO */ },
                cartCount = cartCount,
                scrollBehavior = scrollBehavior,
                scrollState = scrollState,
                onFavoriteClick = {}
            )
            CategoryRow(categories = categories, selectedIndex = selectedCategoryIndex) { index ->
                selectedCategoryIndex = index
            }
            ProductGrid(products = productList, scrollBehavior = scrollBehavior, scrollState,onclick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryRow(categories: List<Category>, selectedIndex: Int, onCategoryClick: (Int) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_2) // Add spacing between items
    ) {
        items(categories.size) { index ->
            val isSelected = index == selectedIndex
            Column(
                modifier = Modifier
                    .padding(end = BwDimensions.PADDING_8)
                    .background(
                        if (isSelected) onPrimary else Color.White, // Background color based on selection
                        CircleShape // Or use a different shape if preferred
                    )
                    .clickable(onClick = { onCategoryClick(index) },
                        indication = null, // Disable the ripple effect if it looks like a shadow
                        interactionSource = remember { MutableInteractionSource() }),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = BwDimensions.PADDING_4),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_4)
                ) {
                    AsyncImage(
                        model = categories[index].imageUrl,
                        contentDescription = "category",
                        modifier = Modifier
                            .size(30.dp)
                            .background(
                                if (isSelected) onPrimary else background, CircleShape
                            )
                            .padding(BwDimensions.PADDING_4)
                    )
                    CommonText(
                        text = categories[index].name,
                        fontSize = BwDimensions.FONT_10,
                        color = if (isSelected) Color.White else Color.Gray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductGrid(
    products: List<Product>,
    scrollBehavior: TopAppBarScrollBehavior,
    scrollState: LazyGridState,
    onclick: () -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        state = scrollState,
        content = {
            items(products.size) { index ->
                ProductItem(product = products[index],onclick)
            }
        })
}

@Composable
fun ProductItem(product: Product, onclick: () -> Unit) {
    var isLiked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        horizontalAlignment = Alignment.Start
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(
                        1.dp,
                        shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS),
                        color = Color.LightGray
                    )
                    .clickable(
                        onClick = { onclick() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
            )
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(BwDimensions.PADDING_4)
                    .align(Alignment.BottomEnd),
                colors = CardDefaults.cardColors(containerColor = background)
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(horizontal = BwDimensions.PADDING_4),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Wishlist",
                        modifier = Modifier
                            .clickable(
                                onClick = { isLiked = !isLiked },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                            .size(20.dp)
                            .background(shape = CircleShape, color = background),
                        tint = if (isLiked) Color.Red else Color.Gray)
                    CommonText(
                        text = "1.5k",
                        fontSize = BwDimensions.FONT_10,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                    )
                }
            }
        }
        CommonText(
            text = product.name,
            fontSize = BwDimensions.TITTLE_FONT_SIZE,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier,
            textAlign = TextAlign.Start
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

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = R.drawable.greendiscount,
                contentDescription = "sell",
                modifier = Modifier.size(20.dp)
            )
            CommonText(
                text = " offer price ${stringResource(id = R.string.price_format, product.price)}",
                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                color = darkGreen,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
            )
        }
        StarRating(rating = 1.5f) {

        }
    }
}


@Composable
fun StarRating(
    rating: Float, // Current rating (e.g., 4.5)
    onRatingChange: (Float) -> Unit // Callback to handle rating change
) {
    Row(
        modifier = Modifier.wrapContentSize()
    ) {
        (1..5).forEach { starIndex ->
            val starValue = starIndex.toFloat()
            val isFullStar = rating >= starValue
            val isHalfStar = !isFullStar && rating >= starValue - 0.5f

            Icon(
                imageVector = when {
                    isFullStar -> Icons.Filled.Star
                    isHalfStar -> Icons.AutoMirrored.Filled.StarHalf
                    else -> Icons.Outlined.Star
                },
                contentDescription = "Star Rating",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onRatingChange(starValue) }, // Handle star click
                tint = when {
                    isFullStar -> Color.Gray
                    isHalfStar -> Color.Gray
                    else -> Color.LightGray // Light gray for stars not part of the rating
                }
            )
        }
    }
}


@Stable
data class Product(val name: String, val price: Double, val imageUrl: Int)

@Stable
data class Category(val name: String, val imageUrl: Int)
