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
    state: CheckDrawUIState,
    qrCodeBitmap: Bitmap?,
    onClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Lottery Draw",
            style = typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().
            padding(vertical = 10.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is CheckDrawUIState.Loading -> {
                    CircularProgressIndicator()
                }

                is CheckDrawUIState.Loaded -> {
                    val numbers = (state as CheckDrawUIState.Loaded).numbers
                    if (numbers.size >= 7) {
                        TicketView(
                            title = "LOTTO",
                            drawDate = "2024-07-18",
                            drawId = "draw-123",
                            numbers = numbers,
                            qrCodeBitmap = qrCodeBitmap
                        )
                    } else {
                        Text("Not enough numbers generated")
                    }
                }

                is CheckDrawUIState.Error -> {
                    val message = (state as CheckDrawUIState.Error).message
                    Text("Error: $message")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

        }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp).align(alignment = Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Absolute.SpaceAround){
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
        state = CheckDrawUIState.Loaded(
            listOf(1,2,3,4,5,6,66)
        ),
        qrCodeBitmap = null,
        onClick = {},
        {}
    )
}


