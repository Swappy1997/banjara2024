package com.example.banjaraworld.navigation

import androidx.annotation.StringRes
import com.example.banjaraworld.R
import kotlinx.serialization.Serializable


@Serializable
sealed class BottomBarScreen(
    val route: String, @StringRes val resourceId: Int=0,val icon:Int=0) {

    @Serializable
     object HomeBottomBarScreen : BottomBarScreen(
        "home_screen",
        resourceId = R.string.home_screen,
        icon = R.drawable.home
    )
    @Serializable
     object MarriageBottomBarScreen :
        BottomBarScreen(
            "marriage_screen",
            icon = R.drawable.wedding,
            resourceId = R.string.marriage_screen
        )

    @Serializable
    data object ShoppingBottomBarScreen :
        BottomBarScreen(
            "shopping_screen",
            icon = R.drawable.shopping,
            resourceId = R.string.shopping_screen
        )

    @Serializable
     object ProfileBottomBarScreen :
        BottomBarScreen(
            "profile_screen",
            icon = R.drawable.user,
            resourceId = R.string.profile_screen
        )
}