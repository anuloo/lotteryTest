package com.mkado.techtest.lotterytest.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.ui.view.component.LotteryItem
import com.mkado.techtest.lotterytest.ui.view.viewmodel.LotteryViewModel

@Composable
fun LotteryListScreen(
    lotteryData: List<Lottery>,
    onLotteryClicked: (String) -> Unit,
    onRefreshClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Title at the top
            Text(
                text = "Latest Draws",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = typography.titleLarge,
                textAlign = TextAlign.Center
            )

            // Main content
            Box(modifier = Modifier.weight(1f)) {
                if (lotteryData.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(64.dp))
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(lotteryData) { lottery ->
                            LotteryItem(
                                lottery = lottery,
                                onClick = { onLotteryClicked(lottery.id) }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Optional spacer for separation

            // Button at the bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = onRefreshClicked) {
                    Text("Refresh Lottery")
                }
            }
        }
    }
}



