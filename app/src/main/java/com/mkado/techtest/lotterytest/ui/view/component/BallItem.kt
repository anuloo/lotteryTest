package com.mkado.techtest.lotterytest.ui.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BallItem(ballNumber: String?, ballColor: Color, size: Float) {
    Box(
        modifier = Modifier.padding(horizontal = 5.dp)
            .size(size.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = ballColor, shape = CircleShape
                )
                .border(1.dp, Color.Black, CircleShape)

        )
        Text(
            text = ballNumber.toString(),
            style = TextStyle(
                color = Color.White,
                fontSize = (size / 2).sp, // Adjust text size based on ball size
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview
@Composable
fun BallItemPreview() {
    BallItem(ballNumber = "66", ballColor = Color.Blue, size = 18f)
}
