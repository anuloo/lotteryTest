package com.mkado.techtest.lotterytest.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mkado.techtest.lotterytest.ui.view.component.BottomNavigationBar
import com.mkado.techtest.lotterytest.ui.view.viewmodel.CheckDrawViewModel
import com.mkado.techtest.lotterytest.ui.view.viewmodel.LotteryDetailViewModel
import com.mkado.techtest.lotterytest.ui.view.viewmodel.LotteryListViewModel
import com.mkado.techtest.lotterytest.ui.view.viewmodel.QRCodeGeneratorViewModel

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
            composable("lotteryList") {
                val viewModel: LotteryListViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                LotteryListScreen(
                    state = state,
                    onLotteryClicked = { lotteryId ->
                        navController.navigate("lotteryDetail/$lotteryId")
                    },
                    onRefreshClicked = { viewModel.refreshData() }
                )
            }
            composable("checkDraw") {
                val checkDrawViewModel: CheckDrawViewModel = hiltViewModel()
                val qrCodeGeneratorViewModel: QRCodeGeneratorViewModel = hiltViewModel()
                val checkDrawState by checkDrawViewModel.state.collectAsState()
                val qrCodeState by qrCodeGeneratorViewModel.state.collectAsState()

                // Trigger fetching lottery data
                LaunchedEffect(Unit) {
                    checkDrawViewModel.generateNumbers()
                }

                // Trigger QR code generation when lottery data is successfully fetched
                LaunchedEffect(checkDrawState) {
                    if (checkDrawState is CheckDrawUIState.Loaded) {
                        val lotteryData = (checkDrawState as CheckDrawUIState.Loaded).numbers
                        qrCodeGeneratorViewModel.generateQRCode(lotteryData.joinToString())
                    }
                }

                CheckDrawScreen(
                    checkDrawState = checkDrawState,
                    qrCodeState = qrCodeState,
                    onClick = { checkDrawViewModel.generateNumbers() },
                    onBackClick = { navController.navigate("lotteryList") }
                )
            }

            composable(
                "lotteryDetail/{lotteryId}",
                arguments = listOf(navArgument("lotteryId") { type = NavType.StringType })
            ) { backStackEntry ->
                val viewModel: LotteryDetailViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                val lotteryId = backStackEntry.arguments?.getString("lotteryId")
                lotteryId?.let {
                    viewModel.getLotteryById(it)
                    LotteryDetailScreen(
                        lottery = state
                    )
                }
            }
        }
    }

}