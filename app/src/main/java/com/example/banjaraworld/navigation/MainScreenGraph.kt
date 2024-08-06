package com.example.banjaraworld.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.banjaraworld.presentation.HomeScreen
import com.example.banjaraworld.presentation.ProfileScreen
import com.example.banjaraworld.presentation.ShoppingScreen
import com.example.banjaraworld.presentation.commonwidgets.MarriageSheet

@Composable
fun MainScreenGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.HomeBottomBarScreen.route,
        route = Graph.MAIN
    ) {
        composable(BottomBarScreen.HomeBottomBarScreen.route) {
            HomeScreen()
        }
        composable(BottomBarScreen.ProfileBottomBarScreen.route) {
            ProfileScreen()
        }
        composable(BottomBarScreen.ShoppingBottomBarScreen.route) {
            ShoppingScreen()
        }
        composable(BottomBarScreen.MarriageBottomBarScreen.route) {
            MarriageSheet {
                navController.navigate(Graph.MARRIAGE)
            }
        }
        marriageNavGraph(navController)
    }
}
