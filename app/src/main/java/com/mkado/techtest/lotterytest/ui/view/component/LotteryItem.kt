package com.mkado.techtest.lotterytest.ui.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.ui.theme.DarkYellow
import com.mkado.techtest.lotterytest.ui.theme.LotteryTestTheme
import com.mkado.techtest.lotterytest.ui.theme.Orange
import com.mkado.techtest.lotterytest.ui.theme.Purple40

@Composable
fun LotteryItem(
    lottery: Lottery,
    onClick:() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row() {
                Text(text = "Top Prize: ${lottery.topPrize}", modifier = Modifier.weight(1f))
                Text(text = "Draw Date: ${lottery.drawDate}")
            }
        }
    }
}

@Preview
@Composable
fun LotteryItemPreview() {
    LotteryTestTheme {
        LotteryItem(lottery = Lottery(
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
        )){}
    }
}
