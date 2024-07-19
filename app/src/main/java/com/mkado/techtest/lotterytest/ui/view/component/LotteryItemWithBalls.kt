package com.mkado.techtest.lotterytest.ui.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.ui.theme.DarkYellow
import com.mkado.techtest.lotterytest.ui.theme.Green
import com.mkado.techtest.lotterytest.ui.theme.LotteryTestTheme
import com.mkado.techtest.lotterytest.ui.theme.Orange
import com.mkado.techtest.lotterytest.ui.theme.Purple40

@Composable
fun LotteryItemWithBalls(lottery: Lottery?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            Text(text = "ID: ${lottery?.id}")
            Row {
                Text(text = "Top Prize: ${lottery?.topPrize}", modifier = Modifier.weight(1f))
                Text(text = "Draw Date: ${lottery?.drawDate}")
            }
            Row(modifier = Modifier.padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically){
                BallItem(ballNumber = lottery?.number1, ballColor = Color.Blue, size = 40f)
                BallItem(ballNumber = lottery?.number2, ballColor = Orange, size = 40f)
                BallItem(ballNumber = lottery?.number3, ballColor = DarkYellow, size = 40f)
                BallItem(ballNumber = lottery?.number4, ballColor = Color.Green, size = 40f)
                BallItem(ballNumber = lottery?.number5, ballColor = Purple40, size = 40f)
                BallItem(ballNumber = lottery?.number6, ballColor = Green, size = 40f)
                BallItem(ballNumber = lottery?.bonusBall, ballColor = Color.Red, size = 52f)
            }
        }
    }
}

@Preview
@Composable
fun LotteryItemWithBallsPreview() {
    LotteryTestTheme {
        LotteryItemWithBalls(lottery = Lottery(
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
        ))
    }
}
