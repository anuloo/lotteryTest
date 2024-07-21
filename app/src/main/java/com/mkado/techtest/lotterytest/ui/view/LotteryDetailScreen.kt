package com.mkado.techtest.lotterytest.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.ui.theme.LotteryTestTheme
import com.mkado.techtest.lotterytest.ui.view.component.LotteryItemWithBalls

@Composable
fun LotteryDetailScreen(
    lottery: Lottery?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lottery Details",
            style = typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LotteryItemWithBalls(lottery = lottery)
    }
}

@Preview
@Composable
fun LotteryDetailScreenPreview(){
    LotteryTestTheme {
        LotteryDetailScreen(
            lottery = Lottery(
                "",
                "2023-05-15",
                "draw-1"
                , "10",
                "23",
                "36",
                "47",
                "21",
                "52",
                4000000
            )
        )
    }
}
