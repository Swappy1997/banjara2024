package com.example.banjaraworld.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banjaraworld.R
import com.example.banjaraworld.ui.theme.onPrimary


@Composable
fun BottomAppBarCompose(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavigationItems = listOf(
        BottomBarScreen.HomeBottomBarScreen,
        BottomBarScreen.ProfileBottomBarScreen,
        BottomBarScreen.ShoppingBottomBarScreen,
        BottomBarScreen.MarriageBottomBarScreen
    )
    val destination = bottomNavigationItems.any { it.route == currentRoute }

    var selectedItem by remember { mutableStateOf(bottomNavigationItems.first()) }
    if (destination) {

        Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(5.dp)) {
            BottomAppBar(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))

                    .drawWithContent {
                        drawContent()
                        drawLine(
                            color = Color.White,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 10f
                        )
                        val redPortion =
                            bottomNavigationItems.indexOf(selectedItem) * (size.width / bottomNavigationItems.size + 2)


                        drawLine(
                            color = onPrimary,
                            start = Offset(redPortion, 0f),
                            end = Offset(
                                redPortion + (size.width / bottomNavigationItems.size),
                                0f
                            ),
                            strokeWidth = 10f
                        )
                    },
                containerColor = Color.White,
                tonalElevation = 10.dp,

                ) {

                bottomNavigationItems.forEach { screen ->
                    IconButton(
                        onClick = {
                            selectedItem = screen
                            navController.navigate(screen.route) {
                                popUpTo(getPopDestinationId(screen)) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                painter = painterResource(screen.icon),
                                contentDescription = null,
                                tint = if (currentRoute == screen.route) onPrimary
                                else Color.Gray,
                                modifier = Modifier.size(15.dp)

                            )
                            Text(
                                text = stringResource(screen.resourceId),
                                textAlign = TextAlign.Center,
                                fontSize = 8.sp,
                                color = if (currentRoute == screen.route) Color.Black else Color.Gray

                            )
                        }


                    }
                }
            }

        }
    }

}


private fun getPopDestinationId(screen: BottomBarScreen): Int {
    return when (screen) {
        BottomBarScreen.HomeBottomBarScreen -> R.string.home_screen
        BottomBarScreen.ProfileBottomBarScreen -> R.string.profile_screen
        BottomBarScreen.ShoppingBottomBarScreen -> R.string.shopping_screen
        BottomBarScreen.MarriageBottomBarScreen -> R.string.marriage_screen
        else -> R.string.home_screen // Default to home screen
    }
}