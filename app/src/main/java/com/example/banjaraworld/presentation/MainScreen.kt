package com.example.banjaraworld.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.banjaraworld.navigation.AppBar
import com.example.banjaraworld.navigation.BottomAppBarCompose
import com.example.banjaraworld.navigation.BottomNavigationGraph
import com.example.banjaraworld.navigation.Graph
import com.example.banjaraworld.ui.theme.background
import kotlinx.coroutines.launch

fun isMarriageFlowRoute(currentRoute: String?): Boolean {
    return currentRoute?.startsWith(Graph.MARRIAGE) == true
}

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

    val showAppBar = !isMarriageFlowRoute(currentRoute)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .navigationBarsPadding(),
            topBar = {
                if (showAppBar) {
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

            BottomNavigationGraph(navController = navController, paddingValues)
        }
    }
}
