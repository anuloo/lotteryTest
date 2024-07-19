package com.mkado.techtest.lotterytest.ui.view.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import com.mkado.techtest.lotterytest.ui.theme.LotteryTestTheme
import com.mkado.techtest.lotterytest.ui.theme.Pink80
import com.mkado.techtest.lotterytest.ui.view.component.BallItem

@Composable
fun TicketView(
    title: String,
    drawDate: String,
    drawId: String,
    numbers: List<Int>,
    qrCodeBitmap: Bitmap?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(Pink80.copy(alpha = 0.5f),Pink80)
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "Draw Date: $drawDate", fontSize = 12.sp)
                Text(text = "ID: $drawId", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider(color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "YOUR NUMBERS",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            numbers.forEach { number ->
                BallItem(ballNumber = number.toString(), ballColor = Color.Black, size = 40f)
                Spacer(modifier = Modifier.width(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider(color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "LUCKY DIP",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "CHECK IF YOU WON",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        qrCodeBitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "QR Code",
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview
fun TicketViewPreview() {
    LotteryTestTheme {
        TicketView(
            title = "LOTTO",
            drawDate = "2024-07-18",
            drawId = "draw-123",
            numbers = listOf(10, 23, 36, 47, 21, 52),
            qrCodeBitmap = null
        )
    }
}
