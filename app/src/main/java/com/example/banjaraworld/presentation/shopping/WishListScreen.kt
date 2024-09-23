package com.example.banjaraworld.presentation.shopping

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.Product
import com.example.banjaraworld.presentation.ProductItem
import com.example.banjaraworld.presentation.commonwidgets.CommonAddToBag
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishListScreen(modifier: Modifier = Modifier) {
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
    val scrollState = rememberLazyGridState()
    Box {
        Column {

            CommonAppBar(
                text = "Wishlist",
                onCartClick = { /*TODO*/ },
                onBackClick = { /*TODO*/ },
                onFavoriteClick = { /*TODO*/ },
                showFavoriteIcon = false,
                showSearchIcon = false,
                scrollState = scrollState
            )
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize(),
                content = {
                    items(productList.size) { index ->
                        Column {
                            ProductItem(product = productList[index], onclick = {})
                            WishListButton()
                        }
                    }
                })
        }
    }
}

@Composable
fun WishListProductCard(modifier: Modifier = Modifier) {


}

@Composable
fun WishListButton(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = BwDimensions.PADDING_2),
        horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Delete,
            contentDescription = "delete",
            modifier = Modifier
                .size(20.dp)
        )

        CommonAddToBag {

        }
    }
}