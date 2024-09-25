package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.EmojiPeople
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.ui.theme.onPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonAppBar(
    modifier: Modifier = Modifier,
    text: String,
    onCartClick: () -> Unit,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    cartCount: State<Int>? = null,
    isFavorite: Boolean = false,
    showCartIcon: Boolean = true,
    showSearchIcon: Boolean = true,
    showFavoriteIcon: Boolean = true,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    scrollState: LazyGridState
) {
    CenterAlignedTopAppBar(
        title = {
            CommonText(
                text = text,
                fontSize = BwDimensions.FONT_14,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp) // Adjust as needed
            )
        },
        modifier = if (scrollState.firstVisibleItemIndex > 0) modifier.shadow(elevation = 4.dp) else modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            scrolledContainerColor = Color.White,
        ),
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            if (showSearchIcon) {
                IconButton(onClick = { /* Implement search functionality here */ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }

            if (showFavoriteIcon) {
                IconButton(onClick = { onFavoriteClick() }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.FavoriteBorder else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                    )
                }
            }

            if (showCartIcon) {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { onCartClick() }) {
                    Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "Cart")
                    if ((cartCount?.value ?: 0) > 0) {
                        CommonText(
                            text = "${cartCount?.value ?: 0}",
                            fontSize = BwDimensions.FONT_11,
                            color = onPrimary,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(start = 8.dp)
                        )
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonAppBar2(
    modifier: Modifier = Modifier,
    text: String,
    onCartClick: () -> Unit,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    cartCount: State<Int>? = null,
    favoriteCount: State<Int>? = null, // Add favorite count
    isFavorite: Boolean = false,
    showCartIcon: Boolean = true,
    showFilterIcon: Boolean = true,
    showSearchIcon: Boolean = true,
    showFavoriteIcon: Boolean = true,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    scrollState: LazyListState,
    onFilterClick: () -> Unit,
    showUserProfile: Boolean = false,
    onUserProfileClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            CommonText(
                text = text,
                fontSize = BwDimensions.FONT_14,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },
        modifier = if (scrollState.firstVisibleItemIndex > 0) modifier.shadow(elevation = 0.dp) else modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            scrolledContainerColor = Color.White,
        ),
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            if (showFilterIcon) {
                IconButton(onClick = { onFilterClick() }) {
                    Icon(imageVector = Icons.Outlined.FilterAlt, contentDescription = "Search")
                }
            }
            if (showSearchIcon) {
                IconButton(onClick = { /* Implement search functionality here */ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }


            if (showFavoriteIcon) {
                BadgedBox(modifier = Modifier
                    .padding( end = 12.dp), badge = {
                    Badge(containerColor = onPrimary) {
                        if (favoriteCount != null) {
                            CommonText(
                                text = "3",
                                fontSize = BwDimensions.FONT_8,
                                color = Color.Black,
                                fontWeight = FontWeight.Medium
                            )
                        }

                    }
                }) {
                    Icon(
                        imageVector = if (favoriteCount != null) Icons.Outlined.FavoriteBorder else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(33.dp)
                            .padding(2.dp)
                    )
                }

            }

            if (showCartIcon) {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { onCartClick() }) {
                    Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "Cart")
                    if ((cartCount?.value ?: 0) > 0) {
                        CommonText(
                            text = "${cartCount?.value ?: 0}",
                            fontSize = BwDimensions.FONT_11,
                            color = Color.Red, // Adjust the color for visibility
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(start = 8.dp)
                        )
                    }
                }
            }
            if (showUserProfile) {
                IconButton(onClick = { onUserProfileClick() }) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Search",
                        Modifier
                            .size(30.dp)
                            .border(shape = CircleShape, width = 1.dp, color = Color.Gray)
                            .padding(2.dp)

                    )
                }
            }

        },
        scrollBehavior = scrollBehavior,
    )
}
