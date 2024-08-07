package com.mkado.techtest.lotterytest.ui.view

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mkado.techtest.lotterytest.ui.view.component.TicketView

@Composable
fun CheckDrawScreen(
    checkDrawState: CheckDrawUIState,
    qrCodeState: QRCodeGenerationState,
    onClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Lottery Draw",
            style = typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (checkDrawState) {
                is CheckDrawUIState.Loading -> {
                    CircularProgressIndicator()
                }

                is CheckDrawUIState.Loaded -> {
                    val numbers = checkDrawState.numbers
                    if (numbers.size >= 6) {
                        TicketView(
                            title = "LOTTO",
                            drawDate = "2024-07-18",
                            drawId = "draw-123",
                            numbers = numbers,
                            qrCodeBitmap = (qrCodeState as? QRCodeGenerationState.Loaded)?.bitmap
                        )
                    } else {
                        Text("Not enough numbers generated")
                    }
                }

                is CheckDrawUIState.Error -> {
                    val message = checkDrawState.message
                    Text("Error: $message")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .align(alignment = Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            Button(onClick = onBackClick) {
                Text("Back to List")
            }
            Button(onClick = onClick) {
                Text("Draw Lottery")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckDrawScreenPreview() {
    CheckDrawScreen(
        checkDrawState = CheckDrawUIState.Loaded(
            numbers = listOf(1, 2, 3, 4, 5, 6, 66)
        ),
        qrCodeState = QRCodeGenerationState.Loaded(
            bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888) // Example bitmap
        ),
        onClick = {},
        onBackClick = {}
    )
}



