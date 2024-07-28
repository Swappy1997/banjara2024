package com.example.banjaraworld.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.banjaraworld.presentation.HomeScreen
import com.example.banjaraworld.presentation.MarriageScreen
import com.example.banjaraworld.presentation.ProfileScreen
import com.example.banjaraworld.presentation.ShoppingScreen
import com.example.banjaraworld.presentation.commonwidgets.MarriageSheet


@Composable
fun BottomNavigationGraph(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(Screen.ShoppingScreen.route) {
            ShoppingScreen()
        }
        marriageNavGraph(navController)
    }
}

