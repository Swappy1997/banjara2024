package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
    cartCount: State<Int>?,
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
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp) // Adjust as needed
                    .clickable { onCartClick() } // Add click functionality if needed
            ) {
                Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "cart")
                if ((cartCount?.value ?: 0) > 0) {
                    CommonText(
                        text = "${cartCount?.value ?: 0}",
                        fontSize = BwDimensions.FONT_11,
                        color = onPrimary,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .align(Alignment.TopEnd) // Adjust position if needed
                            .padding(start = 8.dp)
                    )
                }
            }

        },

        scrollBehavior = scrollBehavior,
    )
}
