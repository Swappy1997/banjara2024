package com.example.banjaraworld.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.banjaraworld.presentation.MarriageScreen
import com.example.banjaraworld.presentation.commonwidgets.MarriageSheet
import com.example.banjaraworld.presentation.marriageregistration.MarriageRegistrationScreen

fun NavGraphBuilder.marriageNavGraph(navHostController: NavHostController) {

    navigation(
        route = Graph.MARRIAGE,
        startDestination = Screen.MarriageScreen.route
    ) {
        composable(MarriageScreen.MarriageScreen.route) {
            MarriageSheet {
                navHostController.navigate(MarriageScreen.MarriageRegistrationScreen.route)
            }
        }
        composable(MarriageScreen.MarriageRegistrationScreen.route) {
            MarriageRegistrationScreen()
        }
    }
}

sealed class MarriageScreen(val route: String) {
    object MarriageScreen : Screen("MARRIAGE_SCREEN")
    object MarriageRegistrationScreen : Screen("MARRIAGE_REGISTRATION_SCREEN")
}