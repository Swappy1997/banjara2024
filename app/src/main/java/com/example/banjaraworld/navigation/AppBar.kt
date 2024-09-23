package com.example.banjaraworld.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Segment
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onSecondary
import com.example.banjaraworld.ui.theme.scrollBackground
import com.example.banjaraworld.ui.theme.surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    firstName: String, onNavigationIconClick: () -> Unit, scrollBehavior: TopAppBarScrollBehavior
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = background),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RectangleShape
    ) {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = surface,
            scrolledContainerColor = scrollBackground,
            titleContentColor = onSecondary,
            actionIconContentColor = onSecondary,
            navigationIconContentColor = onSecondary
        ), title = {
            CommonText(
                text = "Hi $firstName",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }, navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Segment,
                    contentDescription = "Toggle drawer"
                )
            }
        }, actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Notifications, // Replace with the desired icon
                    contentDescription = "notification"
                )
            }
        }, scrollBehavior = scrollBehavior
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppBarPreview(modifier: Modifier = Modifier) {

    AppBar(
        firstName = "John",
        onNavigationIconClick = {},
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    )

}