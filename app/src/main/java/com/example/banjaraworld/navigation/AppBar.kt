package com.example.banjaraworld.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onBackground
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary
import com.example.banjaraworld.ui.theme.onTertiary
import com.example.banjaraworld.ui.theme.scrollBackground
import com.example.banjaraworld.ui.theme.surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    firstName: String,
    onNavigationIconClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = surface,
            scrolledContainerColor = scrollBackground,
            titleContentColor = onSecondary,
            actionIconContentColor = onSecondary,
            navigationIconContentColor = onSecondary
        ),
        title = {
            CommonText(
                text = "Hi $firstName",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = androidx.compose.ui.Modifier
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Notifications, // Replace with the desired icon
                    contentDescription = "notification"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}
