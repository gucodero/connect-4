package com.gucodero.connect4.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun GameDialog(
    title: String,
    textButton: String,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = {  }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 21.dp),
                color = Color.White
            )
            Button(
                onClick = onClick,
                modifier = Modifier.border(
                    width = 2.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFaf4dee),
                            Color(0xFF5bc2fb)
                        )
                    ),
                    shape = MaterialTheme.shapes.small
                ),
            ) {
                Text(
                    text = textButton,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }
        }
    }
}