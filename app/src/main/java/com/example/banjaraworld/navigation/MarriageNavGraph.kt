package com.example.banjaraworld.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.banjaraworld.presentation.MarriageScreen
import com.example.banjaraworld.presentation.marriageregistration.MarriageRegistrationScreen

fun NavGraphBuilder.marriageNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.MARRIAGE,
        startDestination = MarriageScreen.Marriage.route
    ) {
        composable(MarriageScreen.Marriage.route) {
            MarriageScreen(onClick = {
                navHostController.navigate(MarriageScreen.MarriageRegistration.route)
            })
        }
        composable(MarriageScreen.MarriageRegistration.route) {
            MarriageRegistrationScreen()
        }
    }
}

sealed class MarriageScreen(val route: String) {
    object Marriage : MarriageScreen("marriage_screen")
    object MarriageRegistration : MarriageScreen("marriage_registration_screen")
}
