package com.example.banjaraworld.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.banjaraworld.R
import kotlinx.serialization.Serializable


@Serializable
sealed class Screen(
    val route: String, @StringRes val resourceId: Int=0,val icon:Int=0) {

    @Serializable
     object HomeScreen : Screen(
        "home_screen",
        resourceId = R.string.home_screen,
        icon = R.drawable.home
    )
    @Serializable
     object MarriageScreen :
        Screen(
            "marriage_screen",
            icon = R.drawable.wedding,
            resourceId = R.string.marriage_screen
        )

    @Serializable
    data object ShoppingScreen :
        Screen(
            "shopping_screen",
            icon = R.drawable.shopping,
            resourceId = R.string.shopping_screen
        )

    @Serializable
     object ProfileScreen :
        Screen(
            "profile_screen",
            icon = R.drawable.user,
            resourceId = R.string.profile_screen
        )
}