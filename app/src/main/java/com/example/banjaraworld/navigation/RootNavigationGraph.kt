package com.example.banjaraworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.banjaraworld.presentation.MainScreen

@Composable
fun RootNavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navHostController)
        composable(route = Graph.MAIN) {
            MainScreen()
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
    const val MARRIAGE = "MARRIAGE_GRAPH"
}
