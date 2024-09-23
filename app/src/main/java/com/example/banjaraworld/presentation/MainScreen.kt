package com.example.banjaraworld.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.banjaraworld.navigation.AppBar
import com.example.banjaraworld.navigation.BottomAppBarCompose
import com.example.banjaraworld.navigation.BottomBarScreen
import com.example.banjaraworld.navigation.Graph
import com.example.banjaraworld.navigation.MainScreenGraph
import com.example.banjaraworld.ui.theme.BanjaraWorldTheme
import com.example.banjaraworld.ui.theme.background
import kotlinx.coroutines.launch

fun isMarriageFlowRoute(currentRoute: String?): Boolean {
    return currentRoute?.startsWith(Graph.MARRIAGE) == true
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var currentRoute by remember { mutableStateOf<String?>(null) }

    // Update the current route whenever it changes
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute = destination.route
        }
    }

    val showAppbar = listOf(
        BottomBarScreen.HomeBottomBarScreen,
    )

    val destination = showAppbar.any { it.route == currentRoute }
    BanjaraWorldTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .navigationBarsPadding(),
            topBar = {
                if (destination) {
                    AppBar(
                        firstName = "Swapnil",
                        onNavigationIconClick = {
                            scope.launch {
                                state.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            },
            bottomBar = {
                BottomAppBarCompose(navController = navController)
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .background(color = background)
            ) {
                MainScreenGraph(navController = navController, paddingValues)
            }
        }
    }
}
