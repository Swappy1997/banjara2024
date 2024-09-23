package com.example.banjaraworld.presentation.shopping

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcessToPayment(modifier: Modifier = Modifier) {

    val scrollState = rememberLazyGridState()
    Box {
        Column {
            CommonAppBar(
                text = "Order Confirmation",
                onCartClick = { /*TODO*/ },
                onBackClick = { /*TODO*/ },
                onFavoriteClick = { /*TODO*/ },
                scrollState =scrollState,
                showCartIcon = false,
                showFavoriteIcon = false,
                showSearchIcon = false
            )
            LazyColumn {
                item {
                    DefultAddressCard()
                }
                item {
                    
                }
            }
        }
    }
}