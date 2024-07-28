package com.example.banjaraworld.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.banjaraworld.presentation.login_screen.LoginScreen
import com.example.banjaraworld.presentation.splash_screen.SplashScreen
import com.example.banjaraworld.presentation.verfication_screen.VerficationScreen

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(route = Graph.AUTH, startDestination = AuthScreen.Splash.route) {
        composable(AuthScreen.Splash.route) {
            SplashScreen(navController = navHostController)
        }
        composable(AuthScreen.Login.route) {
            LoginScreen(navController = navHostController)
        }
        composable(AuthScreen.Otp.route) {
            VerficationScreen(navigateToHomeScreen = { navHostController.navigate(Graph.MAIN) })
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object Otp : AuthScreen("otp")
    object Splash : AuthScreen("splash")
}
