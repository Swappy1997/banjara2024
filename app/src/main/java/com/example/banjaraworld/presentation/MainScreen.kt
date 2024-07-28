package com.example.banjaraworld.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.example.banjaraworld.navigation.BottomNavigationGraph
import com.example.banjaraworld.ui.theme.background
import com.example.banjaraworld.ui.theme.onBackground
import com.example.banjaraworld.ui.theme.onPrimary
import kotlinx.coroutines.launch


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
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(background)



    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .navigationBarsPadding()
            ,
            topBar = {
                AppBar(
                    "Swapnil",
                    onNavigationIconClick = {
                        scope.launch {
                            state.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    scrollBehavior
                )
            },
            bottomBar = {
                BottomAppBarCompose(navController = navController)
            }
        ) { paddingValues ->
            paddingValues.calculateTopPadding()
            BottomNavigationGraph(navController = navController, paddingValues)
        }
    }
}