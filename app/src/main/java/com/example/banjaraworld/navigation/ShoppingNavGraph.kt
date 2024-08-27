package com.example.banjaraworld.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.banjaraworld.presentation.ShoppingScreen
import com.example.banjaraworld.presentation.shopping.ShoppingDetails

fun NavGraphBuilder.shoppingNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = Graph.SHOP, startDestination = ShoppingScreens.ShoppingDetail.route
    ) {
        composable(route = ShoppingScreens.ShoppingDetail.route) {
            ShoppingDetails(onclick = { navController.navigate(ShoppingScreens.ShoppingCart.route) })
        }

    }

}

sealed class ShoppingScreens(val route: String) {
    object Shopping : ShoppingScreens("shopping")
    object ShoppingDetail : ShoppingScreens("shopping_detail")
    object ShoppingCart : ShoppingScreens("shopping_cart")
    object ShoppingCheckout : ShoppingScreens("shopping_checkout")
    object ShoppingSuccess : ShoppingScreens("shopping_success")
    object ShoppingProfile : ShoppingScreens("shopping_profile")
    object ShoppingHelp : ShoppingScreens("shopping_help")
    object ShoppingFaq : ShoppingScreens("shopping_faq")
    object ShoppingWishlist : ShoppingScreens("shopping_wishlist")
    object ShoppingCompare : ShoppingScreens("shopping_compare")
    object ShoppingAddress : ShoppingScreens("shopping_address")
    object ShoppingPayment : ShoppingScreens("shopping_payment")
    object ShoppingOrder : ShoppingScreens("shopping_order")
    object ShoppingOrderSuccess : ShoppingScreens("shopping_order_success")
    object ShoppingOrderHistory : ShoppingScreens("shopping_order_history")
    object MaleShoppingScreen : ShoppingScreens("male_shopping_screen")
    object FemaleShoppingScreen : ShoppingScreens("female_shopping_screen")
    object KidsShoppingScreen : ShoppingScreens("kids_shopping_screen")
    object BanjaraBazarShoppingScreen : ShoppingScreens("banjara_bazar_shopping_screen")

}