package com.mkado.techtest.lotterytest.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mkado.techtest.lotterytest.ui.view.component.BottomNavigationBar

@Composable
fun LotteryApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = "lotteryList",
            Modifier.padding(paddingValues)
        ) {
            composable("lotteryList") { LotteryListScreen(navController) }
            composable("checkDraw") { CheckDrawScreen(navController) }
        }
    }
}