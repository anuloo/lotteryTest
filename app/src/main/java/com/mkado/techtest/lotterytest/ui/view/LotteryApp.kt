package com.mkado.techtest.lotterytest.ui.view

import android.graphics.Bitmap
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.ui.view.component.BottomNavigationBar
import com.mkado.techtest.lotterytest.ui.view.viewmodel.CheckDrawViewModel
import com.mkado.techtest.lotterytest.ui.view.viewmodel.LotteryViewModel

@Composable
fun LotteryApp() {
    val navController = rememberNavController()
    val viewModel: LotteryViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = "lotteryList",
            Modifier.padding(paddingValues)
        ) {
            composable("lotteryList") {
                LotteryListScreen(
                    lotteryData = (state as? LotteryUIState.Loaded)?.lotteryData ?: emptyList(),
                    onLotteryClicked = { lotteryId ->
                        navController.navigate("lotteryDetail/$lotteryId")
                    },
                    onRefreshClicked = { viewModel.refreshData() }
                )
            }
            composable("checkDraw") {
                val checkDrawViewModel: CheckDrawViewModel = hiltViewModel()
                val checkDrawState by checkDrawViewModel.state.collectAsState()
                val qrCodeBitmap by checkDrawViewModel.qrCodeBitmap.collectAsState()

                if (checkDrawState is CheckDrawUIState.Loading) {
                    checkDrawViewModel.generateNumbers()
                }

                CheckDrawScreen(
                    state = checkDrawState,
                    qrCodeBitmap = qrCodeBitmap,
                    onClick = { checkDrawViewModel.generateNumbers() },
                    onBackClick = { navController.navigate("lotteryList") }
                )
            }
            /*composable("checkDraw") {
                val viewModelDraw: CheckDrawViewModel = hiltViewModel()
                val stateDraw by viewModelDraw.state.collectAsState()
                LaunchedEffect(Unit) {
                    viewModelDraw.generateNumbers()
                }
                CheckDrawScreen(state = stateDraw,
                    onClick = {viewModelDraw.generateNumbers()},
                    onBackClick = { navController.navigate("lotteryList")
            })
            }*/
            composable(
                "lotteryDetail/{lotteryId}",
                arguments = listOf(navArgument("lotteryId") { type = NavType.StringType })
            ) { backStackEntry ->
                val lotteryId = backStackEntry.arguments?.getString("lotteryId")
                lotteryId?.let {
                    LotteryDetailScreen(
                        lottery = viewModel.getLotteryById(it),
                        onBackClicked = { navController.popBackStack() }
                    )
                }
            }
        }
    }

}