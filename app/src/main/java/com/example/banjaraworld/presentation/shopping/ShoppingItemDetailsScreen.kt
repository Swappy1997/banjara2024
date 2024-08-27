package com.example.banjaraworld.presentation.shopping

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import com.example.banjaraworld.R
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingDetails(modifier: Modifier = Modifier, onclick: () -> Unit) {

    val images = listOf(R.drawable.sheet, R.drawable.sheet, R.drawable.sheet)
    val pagerState = rememberPagerState(pageCount = { images.size })
    val cartCount = remember { mutableStateOf(0) }
    val scrollState = rememberLazyGridState()

    // Width of each page and divider
    val pageWidth = 250.dp
    val dividerThickness = 2.dp

    // Animate the horizontal offset of the divider based on the current page index
    val currentPageIndex = pagerState.currentPage
    val dividerOffset by animateDpAsState(
        targetValue = (currentPageIndex * pageWidth).coerceAtLeast(0.dp),
        label = "DividerOffset"
    )

    Box(modifier = modifier.fillMaxSize()) {

        Column {
            CommonAppBar(
                text = "",
                onCartClick = { /*TODO*/ },
                onBackClick = { /*TODO*/ },
                cartCount = cartCount,
                scrollState = scrollState
            )

            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fixed(pageWidth),
                modifier = Modifier.fillMaxWidth()
            ) { index ->

                Card(
                    modifier = Modifier
                        .width(pageWidth)
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RectangleShape
                ) {
                    Column {
                        AsyncImage(
                            model = images[index],
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                        )
                    }
                    // Divider line sliding animation
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Divider(
                            modifier = Modifier
                                .width(pageWidth) // Set width of divider to match page width
                                .offset(x = dividerOffset) // Animate position
                                .padding(horizontal = 10.dp),
                            thickness = dividerThickness,
                            color = Color.Black
                        )
                    }
                }
            }
        }


    }
}
